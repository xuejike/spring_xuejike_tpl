package vip.xuejike.ktpl;

import lombok.Data;

public class AdminJkKtView extends JkKtView {
    private AdminInfo info=new AdminInfo();

    public AdminJkKtView() {
        this.tpl="/kt_tpl/index.ftl";
        this.map.put("info",info);
    }

    public AdminInfo getInfo() {
        return info;
    }

    public void setInfo(AdminInfo info) {
        this.info = info;
    }

    @Override
    public String content() {
        return "";
    }
    @Data
    public static class AdminInfo{
        private String title;
        private String indexUrl;
        private String welcomeUrl;
        private String logoutUrl;
        private String top;
        private String left;
    }
}
