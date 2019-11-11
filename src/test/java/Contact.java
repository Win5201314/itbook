import java.io.Serializable;
import java.util.Date;
import java.util.List;

       /* "addresses": [],
        "note": "",
        "phone_number": ["18682347994"],
        "social_profiles": [],
        "urls": [],
        "modification_date": "2019-06-18 16:41:20",
        "creation_date": "2018-06-06 23:03:42",
        "birthday": "",
        "emails": [],
        "organization_name": "",
        "name": "FDD-X004",
        "job_desc": ""*/
 //URL
//https://aweme.snssdk.com/aweme/v1/upload/contacts/?ac=WIFI&iid=91657871743&device_id=48857178683&os_api=18&app_name=aweme
// &channel=App%20Store&idfa=5166808E-FF9C-4173-96FF-CA47D9CD3461&device_platform=iphone&build_number=86018
// &vid=FF095750-1381-4216-A34F-05E2042EDC77&openudid=341740cc0ee5338befce2275ef04ac745a559f7f
// &device_type=iPhone9,2&app_version=8.6.0&js_sdk_version=1.32.2.1&version_code=8.6.0&os_version=13.2&screen_width=1242&aid=1128&mcc_mnc=46002
public class Contact implements Serializable {
    private List<String> addresses;
    private String note;
    private String phone_number;
    private List<String> social_profiles;
    private List<String> urls;
    private Date modification_date;
    private Date creation_date;
    private String birthday;
    private List<String> emails;
    private String organization_name;
    private String name;
    private String job_desc;
}
