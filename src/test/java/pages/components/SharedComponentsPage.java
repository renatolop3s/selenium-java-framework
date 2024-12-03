package pages.components;

import br.com.renatolop3s.sjf.core.BasePage;

public class SharedComponentsPage<T extends BasePage<T>> extends BasePage<T> {

    private HeaderComponent header;
    private SidebarMenuComponent sidebarMenu;

    public HeaderComponent header() {
        return new HeaderComponent();
    }

    public SidebarMenuComponent openSidebarMenu() {
        return header().openSidebarMenu();
    }
}
