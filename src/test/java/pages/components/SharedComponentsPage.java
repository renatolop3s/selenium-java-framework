package pages.components;

import br.com.renatolop3s.sjf.core.BasePage;

public class SharedComponentsPage<T extends BasePage<T>> extends BasePage<T> {

    public HeaderComponent header() {
        return new HeaderComponent();
    }

    public SidebarMenuComponent openSidebarMenu() {
        return header().openSidebarMenu();
    }
}
