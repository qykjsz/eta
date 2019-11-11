package com.qingyun.mvpretrofitrx.mvp.entity;

public class News {
     /**
         * title : 金财互联：在解决区块链用户规模和资源开销方面有自身特色技术积累
         * content : 据新浪网消息，金财互联（002530.SZ）今日在官方互动平台表示，公司在解决区块链的用户规模（高并发）和资源开销方面有自身的特色技术积累，确实用户规模化后，单位成本会降低；在数字版权、高院的法人画像、供应链金融都有区块链产品及用户在实验验证阶段。
         * time : 1573436282
         */

        private String title;
        private String content;
        private String time;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
}
