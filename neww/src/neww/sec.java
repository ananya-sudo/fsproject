package neww;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Scanner;


public class sec {
private demo2[] sI = new demo2[1000000];

    private String incident_id,state,date,city_or_country,address,n_injured;
int reccount = 0;

private String prikey;

public void getData(){
    @SuppressWarnings("resource")
Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the incident_id: ");
    incident_id = scanner.next();
    System.out.println("Enter the state: ");
          state = scanner.next();
    System.out.println("Enter the date: ");
    date = scanner.next();
    System.out.println("Enter the city_or_country: ");
    city_or_country = scanner.next();
    System.out.println("Enter the address: ");
    address = scanner.next();
    System.out.println("Enter the n_injured: ");
    n_injured = scanner.next();
}
public void add(){
	String data=incident_id +","+  state +","+ date +","+ city_or_country +","+ address +","+n_injured  ;
 try{
RandomAccessFile recordfile = new RandomAccessFile ("C:\\Users\\anany\\eclipse-workspace\\neww\\neww\\src\\gunn.csv","rw");
recordfile.seek(recordfile.length());
long pos = recordfile.getFilePointer();
recordfile.writeBytes(data+"\n");
recordfile.close();

RandomAccessFile indexfile = new RandomAccessFile ("index1.txt","rw");
indexfile.seek(indexfile.length());
indexfile.writeBytes(state+","+pos+"\n");
indexfile.close();
}
catch(IOException e){
System.out.println(e);
}

 
}                    
    public void priIndex(){

String line
                        ,seckey = null,pos = null;
int count = 0;
int sIIndex = 0;
reccount=0;
RandomAccessFile indexfile;
try {
indexfile = new RandomAccessFile("index1.txt","rw");

try {

while((line = indexfile.readLine())!= null){
                                     if(line.contains("*")) {
                continue;
                }
count = 0;
                                                 
                                       
         

StringTokenizer st = new StringTokenizer(line,",");
while (st.hasMoreTokens()){
count+=1;
if(count==1)
   seckey = st.nextToken();
pos = st.nextToken();
                                         
   }
sI[sIIndex] = new demo2();
sI[sIIndex].setRecPos(pos);
sI[sIIndex].setseckey(seckey);
reccount++;
sIIndex++;
                                        if(sIIndex==110000)
                                        {
                                            break;
                                        }
                                }
} catch (IOException e) {

e.printStackTrace();
return;
}
} catch (FileNotFoundException e) {

e.printStackTrace();
return;
}

System.out.println("total records" + reccount);
if (reccount==1) { return;}
sortIndex();
}


public void sortIndex() {
demo2 temp = new demo2();

for(int i=0; i<reccount; i++)
   {
for(int j=i+1; j<reccount; j++) {
if(sI[i].getseckey().compareTo(sI[j].getseckey())  > 0)
           {
               temp.setseckey(sI[i].getseckey());
       temp.setRecPos(sI[i].getRecPos());

        sI[i].setseckey(sI[j].getseckey());
        sI[i].setRecPos(sI[j].getRecPos());

        sI[j].setseckey(temp.getseckey());
        sI[j].setRecPos(temp.getRecPos());
           }
}

}

}
        public void search(){
        System.out.println("Enter the state to search: ");
             Scanner scanner = new Scanner(System.in);
             String state = scanner.next();
             
             
             int oripos = binarySearch(sI, 0, reccount-1, state);
             
             if (oripos == -1) {
                 System.out.println("Record not found in the record file");
                 return ;
             }
             
             int pos = oripos;
             
             do {
                 readFile(pos);
                 pos--;
                 if (pos < 0) { break;}
             }while(sI[pos].getseckey().compareTo(state)==0);
             
             pos = oripos + 1 ;
             
             while(sI[pos].getseckey().compareTo(state)==0 && pos > reccount-1) {
                 readFile(pos);
                 pos++;
             }
        }
 public void readFile(int pos) {
           
            RandomAccessFile recordfile;
            try {
                recordfile = new RandomAccessFile ("C:\\Users\\anany\\eclipse-workspace\\neww\\neww\\src\\gunn.csv","rw");
                try {
                    recordfile.seek(Long.parseLong(sI[pos].getRecPos()));
                    String record = recordfile.readLine();
                    StringTokenizer st = new StringTokenizer(record,",");
                   
                    int count = 0;
                       
                    while (st.hasMoreTokens()){
                             count += 1;
                               if(count==1){
                             
                              String tmp_prikey = st.nextToken();
                               System.out.println("prikey: "+tmp_prikey);
                               this.prikey = tmp_prikey;
                             
                               String tmp_country = st.nextToken();
                               System.out.println("incident_id: "+tmp_country);
                               this.incident_id = tmp_country;
                               
                                String tmp_year = st.nextToken();
                                System.out.println("state "+tmp_year);
                                this.state = tmp_year;
                           
                                 String tmp_commodity = st.nextToken();
                                 System.out.println("date: "+tmp_commodity);
                                 this.date = tmp_commodity;
                               
                                 String tmp_flow = st.nextToken();
                                 System.out.println("city_or_country: "+tmp_flow);
                                 this.city_or_country = tmp_flow;
                             
                                 String tmp_trade = st.nextToken();
                                 System.out.println("address: "+tmp_trade);
                                 this.address = tmp_trade;
                           
                                String tmp_a = st.nextToken();
                                 System.out.println("n_injured: "+tmp_a);
                                 this.n_injured= tmp_a;
                             
                               
                               
                                   System.out.println();
                                   
                                 
                               }
                               else
                                   break;
                       }
                   
                    recordfile.close();
                }
                    catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
               
               
                }
                                       
                catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
 }

        int binarySearch(demo2 s[], int l, int r, String state) {
   
    int mid;
    while (l<=r) {
           
    mid = (l+r)/2;
    if(s[mid].getseckey().compareTo(state)==0) {return mid;}
    if(s[mid].getseckey().compareTo(state)<0) l = mid + 1;
    if(s[mid].getseckey().compareTo(state)>0) r = mid - 1;
    }
    return -1;
    }

  public  void indexing()
  {
         try{
        RandomAccessFile hey=new RandomAccessFile("C:\\Users\\anany\\eclipse-workspace\\neww\\neww\\src\\gunn.csv","rw");
 
   

        RandomAccessFile indexfile=new RandomAccessFile("index1.txt","rw");
        String line;
 long       pos=hey.getFilePointer();
        while((line = hey.readLine())!=null)
        {
            if(line.contains("*")) {
                continue;
                }

            String[] columns=line.split(",");
                                 

                       

         
            indexfile.writeBytes(columns[1]+","+pos+"\n");
            pos=hey.getFilePointer();
        } indexfile.close();
        hey.close();
               
       
         
       
         }
   
    catch(IOException e)
    {
        System.out.println(e);
    }
  }

 public   void delete() throws IOException {
indexing();
     
     System.out.println("Enter the state to delete: ");
     Scanner scanner = new Scanner(System.in);
     String state = scanner.next();
     String ans = "n";
     int pos;
     
     int oripos = binarySearch(sI, 0, reccount-1, state);
     
     if (oripos == -1) {
         System.out.println("Record not found in the record file");
         return ;
     }
     
     pos = oripos;
     
     do {
         readFile(pos);
         
         System.out.println("Do You Want To delete This Record ?(y/n) ");
         ans = scanner.next();
         
         if (ans.compareTo("y")==0) {
             markDelete(pos, state);
         }
         pos--;
         if (pos < 0) { break;}
     }while(sI[pos].getseckey().compareTo(state)==0);
         
     
     pos = oripos + 1 ;
     
     
     while(sI[pos].getseckey().compareTo(state)==0 && pos > reccount-1){
         readFile(pos);
         
         System.out.println("Do You Want To delete This Record ?(y/n) ");
         ans = scanner.next();
         
         if (ans.compareTo("y")==0) {
             markDelete(pos, state);
             break;
         }
         pos++;
         if (pos > reccount-1) { break;}
     }
}
 public void markDelete(int pos, String country) {
     try {
         RandomAccessFile recordfilee = new RandomAccessFile ("C:\\Users\\anany\\eclipse-workspace\\neww\\neww\\src\\gunn.csv","rw");
         RandomAccessFile indexfilee = new RandomAccessFile ("index1.txt","rw");
     
             recordfilee.seek(Long.parseLong(sI[pos].getRecPos()));
             recordfilee.writeBytes("*");
             System.out.println("Done");
             recordfilee.close();
             String line;
             String indexName;
             long indexPos = 0;
             long delPosi;
             //delPosi = indexfilee.getFilePointer();
             while((line = indexfilee.readLine())!=null) {
                 if(line.contains("*"))
                     continue;
                 StringTokenizer st = new StringTokenizer(line,",");
                delPosi = indexfilee.getFilePointer();
                 delPosi = delPosi - (line.length()+2);
                 
                 //System.out.println("Delposi: " + delPosi);
                 
                 while(st.hasMoreTokens()) {
                     indexName=st.nextToken();
                     indexPos= Long.parseLong(st.nextToken());
                     //System.out.println("indexPos: " + indexPos);
                     //System.out.println("getrecpos: " + Long.parseLong(sI[pos].getRecPos()));
                     if(indexName.equals(country) && indexPos == Long.parseLong(sI[pos].getRecPos()) ) {
                         indexfilee.seek(delPosi);
                         indexfilee.writeBytes("*");
                         indexfilee.close();
                         System.out.println("Deleted");
                         indexing();
                         return;
                     }
                 }
             }
             }
         
         catch (Exception e) {
             e.printStackTrace();
         }
 }

}
