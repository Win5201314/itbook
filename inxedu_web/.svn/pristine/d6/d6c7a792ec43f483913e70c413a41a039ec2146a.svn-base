package com.inxedu.os.edu.util.qq;

import com.qq.connect.QQConnectException;
import com.qq.connect.QQConnectResponse;
import com.qq.connect.javabeans.Avatar;
import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;

import java.io.Serializable;

/**
 * @author www.inxedu.com
 */
public class InxeduUserInfoBean extends QQConnectResponse implements Serializable {
    private static final long serialVersionUID = 5606709876246698659L;
    private Avatar avatar = new Avatar("");
    private String nickname;
    private String gender;
    private boolean vip;
    private int level;
    private boolean yellowYearVip;
    private int ret;
    private String msg;
    private String figureurl_qq_1;//qq头像40*40地址
    private String figureurl_qq_2;//qq头像100*100地址

    public String getNickname() {
        return this.nickname;
    }

    public String getFigureurl_qq_1() {
        return this.figureurl_qq_1;
    }
    public String getFigureurl_qq_2() {
        return this.figureurl_qq_2;
    }

    public String getGender() {
        return this.gender;
    }

    public boolean isVip() {
        return this.vip;
    }

    public Avatar getAvatar() {
        return this.avatar;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isYellowYearVip() {
        return this.yellowYearVip;
    }

    public int getRet() {
        return this.ret;
    }

    public String getMsg() {
        return this.msg;
    }

    public InxeduUserInfoBean(JSONObject json) throws QQConnectException {
        this.init(json);
    }

    private void init(JSONObject json) throws QQConnectException {
        if(json != null) {
            try {
                this.ret = json.getInt("ret");
                if(0 != this.ret) {
                    this.msg = json.getString("msg");
                } else {
                    this.msg = "";
                    this.nickname = json.getString("nickname");
                    this.gender = json.getString("gender");
                    this.vip = json.getInt("vip") == 1;
                    this.avatar = new Avatar(json.getString("figureurl"), json.getString("figureurl_1"), json.getString("figureurl_2"));
                    this.level = json.getInt("level");
                    this.yellowYearVip = json.getInt("is_yellow_year_vip") == 1;
                    this.figureurl_qq_1 = json.getString("figureurl_qq_1");
                    this.figureurl_qq_2 = json.getString("figureurl_qq_2");
                }
            } catch (JSONException var3) {
                throw new QQConnectException(var3.getMessage() + ":" + json.toString(), var3);
            }
        }

    }

    public int hashCode() {
        boolean prime = true;
        byte result = 1;
        int result1 = 31 * result + (this.nickname == null?0:this.nickname.hashCode());
        return result1;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj == null) {
            return false;
        } else if(this.getClass() != obj.getClass()) {
            return false;
        } else {
            InxeduUserInfoBean other = (InxeduUserInfoBean)obj;
            if(this.nickname == null) {
                if(other.nickname != null) {
                    return false;
                }
            } else if(!this.nickname.equals(other.nickname)) {
                return false;
            }

            return true;
        }
    }

    public String toString() {
        return "UserInfo [nickname : " + this.nickname + " , " + "figureurl30 : " + this.avatar.getAvatarURL30() + " , " + "figureurl50 : " + this.avatar.getAvatarURL50() + " , " + "figureurl100 : " + this.avatar.getAvatarURL100() + " , " + "gender : " + this.gender + " , " + "vip : " + this.vip + " , " + "level : " + this.level + " , " + "isYellowYeaarVip : " + this.yellowYearVip + " , figureurl_qq_1 : " + this.figureurl_qq_1 + " , figureurl_qq_2 : " + this.figureurl_qq_2+ "]";
    }
}
