package hypernet.handler.dialog;

import hypernet.DialogOption;
import hypernet.DialogPlugin;
import hypernet.IntelProvider;
import hypernet.filter.MutableFilterManager;
import hypernet.provider.AdminIntelProvider;
import hypernet.provider.OfficerIntelProvider;

public class Staff extends FilterAware {

    public Staff(DialogOption o, DialogOption p) {
        super(o, p);
    }

    @Override
    protected DialogOption run(DialogPlugin plugin) {
        IntelProvider provider;
        MutableFilterManager filterManager = plugin.getFilterManager();

        if (filterManager.getStaffType().equals(DialogOption.STAFF_TYPE_ADMIN)) {
            plugin.addText("添加了对{星球管理员}的检索.");
            provider = new AdminIntelProvider();
        } else {
            String personality = filterManager.getStaffOfficer().name().substring(8).toLowerCase();
            
            String localizedPersonality = personality;
            localizedPersonality = localizedPersonality.replaceAll("timid", "胆小的");
            localizedPersonality = localizedPersonality.replaceAll("cautious", "谨慎的");
            localizedPersonality = localizedPersonality.replaceAll("steady", "沉着的");
            localizedPersonality = localizedPersonality.replaceAll("aggressive", "激进的");
            localizedPersonality = localizedPersonality.replaceAll("reckless", "鲁莽的");
            
            plugin.addText("添加了对{" + localizedPersonality + "}军官的检索.");
            provider = new OfficerIntelProvider(personality);
        }

        plugin.addNewQuery(provider);
        Menu.forceMenu(plugin);
        return DialogOption.INIT;
    }
}
