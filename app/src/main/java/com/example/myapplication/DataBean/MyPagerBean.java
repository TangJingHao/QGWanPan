package com.example.myapplication.DataBean;

/**
 * @Name：  MyPagerBean
 * @Description： 存储个人信息的类 包括头像 组群 个人信息等 具体再添加
 * @Author： Suzy.Mo
 * @Date： 2021/7/28 1:09
 */
public class MyPagerBean {

    private Boolean flag;
    private DataBean data;
    private String message;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private Integer id;
        private String username;
        private String password;
        private String nickname;
        private Object image;
        private Double space;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public Double getSpace() {
            return space;
        }

        public void setSpace(Double space) {
            this.space = space;
        }
    }
}

