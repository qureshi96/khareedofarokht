package com.example.kd;

    public class SearchModel {
        String name;
        String price;
        String link;
        String image;
        String data;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getPrice1() {
            return price1;
        }

        public void setPrice1(int price1) {
            this.price1 = price1;
        }

        String id;
        int price1;

        public SearchModel(String name, String price, String link, String image, String data, String id, int price1) {
            this.name = name;
            this.price = price;
            this.link = link;
            this.image = image;
            this.data = data;
            this.id = id;
            this.price1 = price1;
        }

        public SearchModel() {
        }



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

