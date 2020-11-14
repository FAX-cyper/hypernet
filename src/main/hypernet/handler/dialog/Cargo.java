package hypernet.handler.dialog;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoPickerListener;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import hypernet.DialogOption;
import hypernet.DialogPlugin;
import hypernet.IntelProvider;
import hypernet.filter.CargoStackFilter;
import hypernet.filter.MutableFilterManager;
import hypernet.helper.CollectionHelper;
import hypernet.helper.MarketHelper;
import hypernet.provider.CargoIntelProvider;

public class Cargo extends FilterAware implements CargoPickerListener {

    private DialogPlugin plugin;

    public Cargo(DialogOption o, DialogOption p) {
        super(o, p);
    }

    @Override
    public void cancelledCargoSelection() {
        plugin.addText("检索取消...");
    }

    @Override
    public void pickedCargo(CargoAPI cargo) {
        if (cargo.isEmpty()) {
            plugin.addText("检索取消...");
            return;
        }
        cargo.sort();
        for (CargoStackAPI cargoStack : cargo.getStacksCopy()) {
            IntelProvider provider = new CargoIntelProvider(cargoStack);
            plugin.addText("添加了对" + cargoStack.getDisplayName() + "的检索.");
            plugin.addNewQuery(provider);
        }
        Menu.forceMenu(plugin);
    }

    @Override
    public void recreateTextPanel(TooltipMakerAPI panel, CargoAPI cargo, CargoStackAPI pickedUp,
            boolean pickedUpFromSource, CargoAPI combined) {
    }

    @Override
    protected DialogOption run(DialogPlugin plugin) {
        this.plugin = plugin;
        MutableFilterManager filterManager = plugin.getFilterManager();
        CargoAPI cargo = getCargo(filterManager);

        if (cargo.isEmpty()) {
            String category = filterManager.getCargoType().getName().substring(7).toLowerCase();
            plugin.addText("对" + category + "的检索无任何结果.");
            return option;
        }

        plugin.getDialog().showCargoPickerDialog("选取检索项目...", "检索", "取消", false, 0f, cargo,
                this);
        return option;
    }

    private CargoAPI getCargo(MutableFilterManager filterManager) {
        List<CargoStackAPI> cargoStacks = findItems(filterManager);
        return makeCargoFromStacks(cargoStacks);
    }

    private List<CargoStackAPI> findItems(MutableFilterManager filterManager) {
        List<SubmarketAPI> submarkets = MarketHelper.getSubmarkets();
        List<CargoStackFilter> cargoStackFilters = filterManager.listCargoFilters();
        List<CargoStackAPI> cargoStacks = new ArrayList<CargoStackAPI>();

        for (SubmarketAPI s : submarkets) {
            List<CargoStackAPI> submarketStacks = s.getCargo().getStacksCopy();
            CollectionHelper.reduce(submarketStacks, cargoStackFilters);
            cargoStacks.addAll(submarketStacks);
        }

        return cargoStacks;
    }

    private CargoAPI makeCargoFromStacks(List<CargoStackAPI> cargoStacks) {
        CargoAPI cargo = Global.getFactory().createCargo(true);
        for (CargoStackAPI cargoStack : cargoStacks) {
            cargo.addFromStack(cargoStack);
        }
        cargo.sort();
        return cargo;
    }
}
