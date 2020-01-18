package com.dongisarang.user.kakao;

public class KakaoApprove {

    private String approved_at;
    private String created_at;
    private Card_info card_info;
    private Amount amount;
    private int quantity;
    private String item_name;
    private String payment_method_type;
    private String partner_order_id;
    private String partner_user_id;
    private String tid;
    private String aid;
    private String cid;

    public String getApproved_at() {
        return approved_at;
    }

    public void setApproved_at(String approved_at) {
        this.approved_at = approved_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Card_info getCard_info() {
        return card_info;
    }

    public void setCard_info(Card_info card_info) {
        this.card_info = card_info;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getPayment_method_type() {
        return payment_method_type;
    }

    public void setPayment_method_type(String payment_method_type) {
        this.payment_method_type = payment_method_type;
    }

    public String getPartner_order_id() {
        return partner_order_id;
    }

    public void setPartner_order_id(String partner_order_id) {
        this.partner_order_id = partner_order_id;
    }

    public String getPartner_user_id() {
        return partner_user_id;
    }

    public void setPartner_user_id(String partner_user_id) {
        this.partner_user_id = partner_user_id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public static class Card_info {
        private String kakaopay_issuer_corp_code;
        private String kakaopay_issuer_corp;
        private String kakaopay_purchase_corp_code;
        private String kakaopay_purchase_corp;
        private String issuer_corp_code;
        private String issuer_corp;
        private String purchase_corp_code;
        private String purchase_corp;
        private String install_month;
        private String approved_id;
        private String card_mid;
        private String card_type;
        private String bin;
        private String interest_free_install;

        public String getKakaopay_issuer_corp_code() {
            return kakaopay_issuer_corp_code;
        }

        public void setKakaopay_issuer_corp_code(String kakaopay_issuer_corp_code) {
            this.kakaopay_issuer_corp_code = kakaopay_issuer_corp_code;
        }

        public String getKakaopay_issuer_corp() {
            return kakaopay_issuer_corp;
        }

        public void setKakaopay_issuer_corp(String kakaopay_issuer_corp) {
            this.kakaopay_issuer_corp = kakaopay_issuer_corp;
        }

        public String getKakaopay_purchase_corp_code() {
            return kakaopay_purchase_corp_code;
        }

        public void setKakaopay_purchase_corp_code(String kakaopay_purchase_corp_code) {
            this.kakaopay_purchase_corp_code = kakaopay_purchase_corp_code;
        }

        public String getKakaopay_purchase_corp() {
            return kakaopay_purchase_corp;
        }

        public void setKakaopay_purchase_corp(String kakaopay_purchase_corp) {
            this.kakaopay_purchase_corp = kakaopay_purchase_corp;
        }

        public String getIssuer_corp_code() {
            return issuer_corp_code;
        }

        public void setIssuer_corp_code(String issuer_corp_code) {
            this.issuer_corp_code = issuer_corp_code;
        }

        public String getIssuer_corp() {
            return issuer_corp;
        }

        public void setIssuer_corp(String issuer_corp) {
            this.issuer_corp = issuer_corp;
        }

        public String getPurchase_corp_code() {
            return purchase_corp_code;
        }

        public void setPurchase_corp_code(String purchase_corp_code) {
            this.purchase_corp_code = purchase_corp_code;
        }

        public String getPurchase_corp() {
            return purchase_corp;
        }

        public void setPurchase_corp(String purchase_corp) {
            this.purchase_corp = purchase_corp;
        }

        public String getInstall_month() {
            return install_month;
        }

        public void setInstall_month(String install_month) {
            this.install_month = install_month;
        }

        public String getApproved_id() {
            return approved_id;
        }

        public void setApproved_id(String approved_id) {
            this.approved_id = approved_id;
        }

        public String getCard_mid() {
            return card_mid;
        }

        public void setCard_mid(String card_mid) {
            this.card_mid = card_mid;
        }

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public String getBin() {
            return bin;
        }

        public void setBin(String bin) {
            this.bin = bin;
        }

        public String getInterest_free_install() {
            return interest_free_install;
        }

        public void setInterest_free_install(String interest_free_install) {
            this.interest_free_install = interest_free_install;
        }
    }

    public static class Amount {
        private int point;
        private int discount;
        private int vat;
        private int tax_free;
        private int total;

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public int getVat() {
            return vat;
        }

        public void setVat(int vat) {
            this.vat = vat;
        }

        public int getTax_free() {
            return tax_free;
        }

        public void setTax_free(int tax_free) {
            this.tax_free = tax_free;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    @Override
    public String toString() {
        return "Kakao{" +
                "approved_at='" + approved_at + '\'' +
                ", created_at='" + created_at + '\'' +
                ", tid='" + tid + '\'' +
                ", aid='" + aid + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}
