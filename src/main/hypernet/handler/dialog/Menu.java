package hypernet.handler.dialog;

import hypernet.DialogOption;
import hypernet.DialogPlugin;
import hypernet.handler.DialogHandler;

public class Menu implements DialogHandler {

    @Override
    public DialogOption handle(DialogPlugin plugin) {
        plugin.addText("返回主页面...");
        forceMenu(plugin);
        return DialogOption.INIT;
    }

    public static void forceMenu(DialogPlugin plugin) {
        plugin.addTitle("超空间检索");
        plugin.addText("还有什么检索申请吗?");
        plugin.showMenu();
    }
}
