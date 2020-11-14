package hypernet.handler.dialog;

import hypernet.DialogOption;
import hypernet.DialogPlugin;
import hypernet.handler.DialogHandler;
import hypernet.handler.DialogHandlerFactory;

public class FilterAware implements DialogHandler {

    protected DialogOption option;
    protected DialogOption previousOption;

    public FilterAware(DialogOption o, DialogOption p) {
        option = o;
        previousOption = p;
    }

    public DialogOption handle(DialogPlugin plugin) {
        if (previousOption.equals(DialogOption.INIT)) {
            String action = option.getName();
            plugin.addTitle(action);
            plugin.addText("请选择过滤项并" + action.toLowerCase() + ".");
            return init(plugin);
        }

        return run(plugin);
    }

    protected DialogOption init(DialogPlugin plugin) {
        DialogHandler handler = DialogHandlerFactory.getFilterHandler(option, previousOption);
        return handler.handle(plugin);
    }

    protected DialogOption run(DialogPlugin plugin) {
        return option;
    }
}
