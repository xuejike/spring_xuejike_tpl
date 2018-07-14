package vip.xuejike.ktpl;

import org.jetbrains.annotations.NotNull;

/**
 * @author xuejike
 */
public class AdminLoginKtView extends JkKtView {

    private LoginInfo info = new LoginInfo();
    public AdminLoginKtView() {
        this.tpl="/kt_tpl/login.ftl";
        this.map.put("info",info);
    }

    @NotNull
    @Override
    public String content() {
        return "";
    }


    public static class LoginInfo{
        private String title;
        private String actionUrl;
        private String userName;
        private String pwd;
        private String msg;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
