package people;

import lombok.Data;

@Data
public class Deals {
    int commission;
    String post, employersId, applicantId;

    public Deals(String employersId, String applicantId, String post, int commission) {
        this.employersId = employersId;
        this.applicantId = applicantId;
        this.commission = commission;
        this.post = post;
    }

   // public int getEmployersId() {
   //     return employersId;
   // }
//
   // public void setEmployersId(int employersId) {
   //     this.employersId = employersId;
   // }
//
   // public int getApplicantId() {
   //     return applicantId;
   // }
//
   // public void setApplicantId(int applicantId) {
   //     this.applicantId = applicantId;
   // }
//
   // public int getCommission() {
   //     return commission;
   // }
//
   // public void setCommission(int commission) {
   //     this.commission = commission;
   // }
//
   // public String getPost() {
   //     return post;
   // }
//
   // public void setPost(String post) {
   //     this.post = post;
   // }
}
