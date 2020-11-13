package hypernet;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import com.fs.starfarer.api.campaign.listeners.EconomyTickListener;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

public class MonthEndListener implements EconomyTickListener {

    public static void register() {
        ListenerManagerAPI listenerManager = Global.getSector().getListenerManager();
        List<MonthEndListener> listeners = listenerManager.getListeners(MonthEndListener.class);
        if (listeners.isEmpty()) {
            MonthEndListener listener = new MonthEndListener();
            listenerManager.addListener(listener);
        }
    }

    @Override
    public void reportEconomyMonthEnd() {
        MonthEndIntel intel = new MonthEndIntel(
                "新的月份开始，超检索网络的信息可能已过期 - 请记得刷新.");
        toggleIntel(intel);
    }

    @Override
    public void reportEconomyTick(int tick) {
        int maxTicks = Global.getSettings().getInt("economyIterPerMonth");
        boolean isSecondToLastTick = maxTicks - tick == 2;
        if (isSecondToLastTick) {
            MonthEndIntel intel = new MonthEndIntel("本月份即将结束，超检索网络的信息可能会过期!");
            toggleIntel(intel);
        }
    }

    private void toggleIntel(BaseIntelPlugin intel) {
        IntelManagerAPI intelManager = Global.getSector().getIntelManager();
        intelManager.addIntel(intel);
        intelManager.removeIntel(intel);
    }
}
