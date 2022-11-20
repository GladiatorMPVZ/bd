package people;

import lombok.Data;

@Data
public class Emploers {

    int id;
    String title, kindEmp, adress, number;

    public Emploers(int id, String title, String kindEmp, String adress, String number) {
        this.id = id;
        this.title = title;
        this.kindEmp = kindEmp;
        this.adress = adress;
        this.number = number;
    }

   // public int getId() {
   //     return id;
   // }
//
   // public void setId(int id) {
   //     this.id = id;
   // }
//
   // public String getTitle() {
   //     return title;
   // }
//
   // public void setTitle(String title) {
   //     this.title = title;
   // }
//
   // public String getKindEmp() {
   //     return kindEmp;
   // }
//
   // public void setKindEmp(String kindEmp) {
   //     this.kindEmp = kindEmp;
   // }
//
   // public String getAdress() {
   //     return adress;
   // }
//
   // public void setAdress(String adress) {
   //     this.adress = adress;
   // }
//
   // public String getNumber() {
   //     return number;
   // }
//
   // public void setNumber(String number) {
   //     this.number = number;
   // }
}
