package com.heitugs.xiannongexpress.model;

import java.util.List;

/**
 * Created by Administrator on 2016/9/3 0003.
 */
public class MsgModel {

    /**
     * data : [{"LoginLimtNumber":0,"Email":"22104416@qq.com","Manager_name":"","pay_number":2,"sfz":"","RegTime":"2009/1/7 11:31:04","OnlineTime":0,"aPoint":17890,"MobileCode":"","isIDcard":0,"OnlineTF":0,"Addfriend":2,"BindTF":0,"Userinfo":"","UserFace":"/var/mobile/Applications/43A28E0B-A218-4930-A36B-D1EE887879D7/Documents/IMG_0212.JPG","isMobile":1,"computer_info":"","email_code":"8629 ","CertNumber":"","UserPassword":"965eb72c92a549dd","userFacesize":"80|80","isAdmin":1,"NickName":"小豆豆","cimg":"","isOpen":0,"enddate":"2041/12/28 10:31:01","IDcardFiles":"","ctype":"","gPoint":0,"Id":31,"LoginNumber":6983,"birthday":"1980/11/11 0:00:00","FriendClass":"","EmailCode":"13897276313601dd8f0341cf82ec8da8","UserGroupNumber":"038885644130","city":"","Sex":"1","ParmConstrNum":0,"CertType":"","click_today":2,"iscompany":0,"click_total":2,"EmailATF":1,"isLock":0,"card_no":"","marriage":0,"Addfriendbs":2,"ePoint":0,"LastIP":"1.58.161.252","SiteID":"0","UserName":"test","cPoint":17890,"UserNum":"99892491029","mobile":"13796666878","cbh":"","PassKey":"a0b923820dcc509a","iPoint":20124,"LastLoginTime":"2016/9/3 11:21:32","PassQuestion":"1","RealName":"aaa"}]
     */
    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public class DataEntity {
        /**
         * LoginLimtNumber : 0
         * Email : 22104416@qq.com
         * Manager_name :
         * pay_number : 2
         * sfz :
         * RegTime : 2009/1/7 11:31:04
         * OnlineTime : 0
         * aPoint : 17890
         * MobileCode :
         * isIDcard : 0
         * OnlineTF : 0
         * Addfriend : 2
         * BindTF : 0
         * Userinfo :
         * UserFace : /var/mobile/Applications/43A28E0B-A218-4930-A36B-D1EE887879D7/Documents/IMG_0212.JPG
         * isMobile : 1
         * computer_info :
         * email_code : 8629
         * CertNumber :
         * UserPassword : 965eb72c92a549dd
         * userFacesize : 80|80
         * isAdmin : 1
         * NickName : 小豆豆
         * cimg :
         * isOpen : 0
         * enddate : 2041/12/28 10:31:01
         * IDcardFiles :
         * ctype :
         * gPoint : 0
         * Id : 31
         * LoginNumber : 6983
         * birthday : 1980/11/11 0:00:00
         * FriendClass :
         * EmailCode : 13897276313601dd8f0341cf82ec8da8
         * UserGroupNumber : 038885644130
         * city :
         * Sex : 1
         * ParmConstrNum : 0
         * CertType :
         * click_today : 2
         * iscompany : 0
         * click_total : 2
         * EmailATF : 1
         * isLock : 0
         * card_no :
         * marriage : 0
         * Addfriendbs : 2
         * ePoint : 0
         * LastIP : 1.58.161.252
         * SiteID : 0
         * UserName : test
         * cPoint : 17890
         * UserNum : 99892491029
         * mobile : 13796666878
         * cbh :
         * PassKey : a0b923820dcc509a
         * iPoint : 20124
         * LastLoginTime : 2016/9/3 11:21:32
         * PassQuestion : 1
         * RealName : aaa
         */
        private int LoginLimtNumber;
        private String Email;
        private String Manager_name;
        private int pay_number;
        private String sfz;
        private String RegTime;
        private int OnlineTime;
        private int aPoint;
        private String MobileCode;
        private int isIDcard;
        private int OnlineTF;
        private int Addfriend;
        private int BindTF;
        private String Userinfo;
        private String UserFace;
        private int isMobile;
        private String computer_info;
        private String email_code;
        private String CertNumber;
        private String UserPassword;
        private String userFacesize;
        private int isAdmin;
        private String NickName;
        private String cimg;
        private int isOpen;
        private String enddate;
        private String IDcardFiles;
        private String ctype;
        private int gPoint;
        private int Id;
        private int LoginNumber;
        private String birthday;
        private String FriendClass;
        private String EmailCode;
        private String UserGroupNumber;
        private String city;
        private String Sex;
        private int ParmConstrNum;
        private String CertType;
        private int click_today;
        private int iscompany;
        private int click_total;
        private int EmailATF;
        private int isLock;
        private String card_no;
        private int marriage;
        private int Addfriendbs;
        private int ePoint;
        private String LastIP;
        private String SiteID;
        private String UserName;
        private int cPoint;
        private String UserNum;
        private String mobile;
        private String cbh;
        private String PassKey;
        private int iPoint;
        private String LastLoginTime;
        private String PassQuestion;
        private String RealName;

        public void setLoginLimtNumber(int LoginLimtNumber) {
            this.LoginLimtNumber = LoginLimtNumber;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public void setManager_name(String Manager_name) {
            this.Manager_name = Manager_name;
        }

        public void setPay_number(int pay_number) {
            this.pay_number = pay_number;
        }

        public void setSfz(String sfz) {
            this.sfz = sfz;
        }

        public void setRegTime(String RegTime) {
            this.RegTime = RegTime;
        }

        public void setOnlineTime(int OnlineTime) {
            this.OnlineTime = OnlineTime;
        }

        public void setAPoint(int aPoint) {
            this.aPoint = aPoint;
        }

        public void setMobileCode(String MobileCode) {
            this.MobileCode = MobileCode;
        }

        public void setIsIDcard(int isIDcard) {
            this.isIDcard = isIDcard;
        }

        public void setOnlineTF(int OnlineTF) {
            this.OnlineTF = OnlineTF;
        }

        public void setAddfriend(int Addfriend) {
            this.Addfriend = Addfriend;
        }

        public void setBindTF(int BindTF) {
            this.BindTF = BindTF;
        }

        public void setUserinfo(String Userinfo) {
            this.Userinfo = Userinfo;
        }

        public void setUserFace(String UserFace) {
            this.UserFace = UserFace;
        }

        public void setIsMobile(int isMobile) {
            this.isMobile = isMobile;
        }

        public void setComputer_info(String computer_info) {
            this.computer_info = computer_info;
        }

        public void setEmail_code(String email_code) {
            this.email_code = email_code;
        }

        public void setCertNumber(String CertNumber) {
            this.CertNumber = CertNumber;
        }

        public void setUserPassword(String UserPassword) {
            this.UserPassword = UserPassword;
        }

        public void setUserFacesize(String userFacesize) {
            this.userFacesize = userFacesize;
        }

        public void setIsAdmin(int isAdmin) {
            this.isAdmin = isAdmin;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public void setCimg(String cimg) {
            this.cimg = cimg;
        }

        public void setIsOpen(int isOpen) {
            this.isOpen = isOpen;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public void setIDcardFiles(String IDcardFiles) {
            this.IDcardFiles = IDcardFiles;
        }

        public void setCtype(String ctype) {
            this.ctype = ctype;
        }

        public void setGPoint(int gPoint) {
            this.gPoint = gPoint;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public void setLoginNumber(int LoginNumber) {
            this.LoginNumber = LoginNumber;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public void setFriendClass(String FriendClass) {
            this.FriendClass = FriendClass;
        }

        public void setEmailCode(String EmailCode) {
            this.EmailCode = EmailCode;
        }

        public void setUserGroupNumber(String UserGroupNumber) {
            this.UserGroupNumber = UserGroupNumber;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public void setParmConstrNum(int ParmConstrNum) {
            this.ParmConstrNum = ParmConstrNum;
        }

        public void setCertType(String CertType) {
            this.CertType = CertType;
        }

        public void setClick_today(int click_today) {
            this.click_today = click_today;
        }

        public void setIscompany(int iscompany) {
            this.iscompany = iscompany;
        }

        public void setClick_total(int click_total) {
            this.click_total = click_total;
        }

        public void setEmailATF(int EmailATF) {
            this.EmailATF = EmailATF;
        }

        public void setIsLock(int isLock) {
            this.isLock = isLock;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public void setMarriage(int marriage) {
            this.marriage = marriage;
        }

        public void setAddfriendbs(int Addfriendbs) {
            this.Addfriendbs = Addfriendbs;
        }

        public void setEPoint(int ePoint) {
            this.ePoint = ePoint;
        }

        public void setLastIP(String LastIP) {
            this.LastIP = LastIP;
        }

        public void setSiteID(String SiteID) {
            this.SiteID = SiteID;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setCPoint(int cPoint) {
            this.cPoint = cPoint;
        }

        public void setUserNum(String UserNum) {
            this.UserNum = UserNum;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setCbh(String cbh) {
            this.cbh = cbh;
        }

        public void setPassKey(String PassKey) {
            this.PassKey = PassKey;
        }

        public void setIPoint(int iPoint) {
            this.iPoint = iPoint;
        }

        public void setLastLoginTime(String LastLoginTime) {
            this.LastLoginTime = LastLoginTime;
        }

        public void setPassQuestion(String PassQuestion) {
            this.PassQuestion = PassQuestion;
        }

        public void setRealName(String RealName) {
            this.RealName = RealName;
        }

        public int getLoginLimtNumber() {
            return LoginLimtNumber;
        }

        public String getEmail() {
            return Email;
        }

        public String getManager_name() {
            return Manager_name;
        }

        public int getPay_number() {
            return pay_number;
        }

        public String getSfz() {
            return sfz;
        }

        public String getRegTime() {
            return RegTime;
        }

        public int getOnlineTime() {
            return OnlineTime;
        }

        public int getAPoint() {
            return aPoint;
        }

        public String getMobileCode() {
            return MobileCode;
        }

        public int getIsIDcard() {
            return isIDcard;
        }

        public int getOnlineTF() {
            return OnlineTF;
        }

        public int getAddfriend() {
            return Addfriend;
        }

        public int getBindTF() {
            return BindTF;
        }

        public String getUserinfo() {
            return Userinfo;
        }

        public String getUserFace() {
            return UserFace;
        }

        public int getIsMobile() {
            return isMobile;
        }

        public String getComputer_info() {
            return computer_info;
        }

        public String getEmail_code() {
            return email_code;
        }

        public String getCertNumber() {
            return CertNumber;
        }

        public String getUserPassword() {
            return UserPassword;
        }

        public String getUserFacesize() {
            return userFacesize;
        }

        public int getIsAdmin() {
            return isAdmin;
        }

        public String getNickName() {
            return NickName;
        }

        public String getCimg() {
            return cimg;
        }

        public int getIsOpen() {
            return isOpen;
        }

        public String getEnddate() {
            return enddate;
        }

        public String getIDcardFiles() {
            return IDcardFiles;
        }

        public String getCtype() {
            return ctype;
        }

        public int getGPoint() {
            return gPoint;
        }

        public int getId() {
            return Id;
        }

        public int getLoginNumber() {
            return LoginNumber;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getFriendClass() {
            return FriendClass;
        }

        public String getEmailCode() {
            return EmailCode;
        }

        public String getUserGroupNumber() {
            return UserGroupNumber;
        }

        public String getCity() {
            return city;
        }

        public String getSex() {
            return Sex;
        }

        public int getParmConstrNum() {
            return ParmConstrNum;
        }

        public String getCertType() {
            return CertType;
        }

        public int getClick_today() {
            return click_today;
        }

        public int getIscompany() {
            return iscompany;
        }

        public int getClick_total() {
            return click_total;
        }

        public int getEmailATF() {
            return EmailATF;
        }

        public int getIsLock() {
            return isLock;
        }

        public String getCard_no() {
            return card_no;
        }

        public int getMarriage() {
            return marriage;
        }

        public int getAddfriendbs() {
            return Addfriendbs;
        }

        public int getEPoint() {
            return ePoint;
        }

        public String getLastIP() {
            return LastIP;
        }

        public String getSiteID() {
            return SiteID;
        }

        public String getUserName() {
            return UserName;
        }

        public int getCPoint() {
            return cPoint;
        }

        public String getUserNum() {
            return UserNum;
        }

        public String getMobile() {
            return mobile;
        }

        public String getCbh() {
            return cbh;
        }

        public String getPassKey() {
            return PassKey;
        }

        public int getIPoint() {
            return iPoint;
        }

        public String getLastLoginTime() {
            return LastLoginTime;
        }

        public String getPassQuestion() {
            return PassQuestion;
        }

        public String getRealName() {
            return RealName;
        }
    }
}
