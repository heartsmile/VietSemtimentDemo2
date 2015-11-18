package main;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;

//import app.server.handling.ServerImpl;

public class ExtractOpinion {

	public static void main(String[] args) {

		/*try {
			ServerImpl server = new ServerImpl();
			
			String demo = "#NV_Cfs5204  :(\n --- #1: 13/11/2015 20:29:13 Form trai BÃ¡ch Khoa: báº¡n nam nÃ o Ä‘Ã³ á»Ÿ trÆ°á»�ng nv hay Ä‘i xe bus sá»‘ 8 Æ¡i, cao á»‘m hay Ä‘á»™i mÅ©, báº¡n nhÃ¬n thá»¥ quÃ¡ mÃ¬nh lÃ m cÃ´ng cá»§a báº¡n dc ko hihi --- #2: 13/11/2015 21:20:14 Hello, It's me How are you? Anh bá»‹ máº¥t bÃ³p nhÆ°ng Ä‘iá»�u anh lo sá»£ nháº¥t chÃ­nh lÃ  máº¥t hÃ¬nh em anh Ä‘á»ƒ trong Ä‘Ã³. Anh bá»‹ hÆ° Ä‘iá»‡n thoáº¡i Ä‘iá»�u anh lo sá»£ nháº¥t lÃ  khi sá»­a xong thÃ¬ máº¥t háº¿t tin nháº¯n vÃ  hÃ¬nh cá»§a em trong mÃ¡y NhÆ°ng tháº­t may máº¯n lÃ  anh váº«n giá»¯ Ä‘Æ°á»£c nhá»¯ng thá»© quÃ½ giÃ¡ Ä‘Ã³ :( LÆ°Æ¡ng TrÆ°á»�ng Giang Æ¡i cÃ²n nhá»› anh khÃ´ng, bá»� cháº·n fb vÃ  ib anh nhÃ© :( MÃ¬nh há»�c Ä�iá»‡n nÃªn viáº¿t khÃ´ng hay mong cÃ¡c báº¡n bá»� qua :(  --- #3: 13/11/2015 21:52:24 VÃ¬ confession há»�i danh tÃ­nh anh tÃ³c cá»§ tá»�i láº§n trÆ°á»›c cá»§a em cÃ³ báº¡n comment báº£o trÆ°á»�ng nhiá»�u cá»§ tá»�i láº¯m nÃ³i tháº¿ sao tÃ¬m Ä‘Æ°á»£c, nÃªn em quyáº¿t Ä‘á»‹nh soáº¡n láº¡i. Tháº­t ra Ä‘i ngang áº£nh máº¥y láº§n nhÆ°ng em cÅ©ng chá»‰ nhÃ¬n má»—i tÃ³c cá»§ tá»�i thÃ´i nÃªn khÃ´ng biáº¿t miÃªu táº£ sao. Chá»‰ nhá»› mang mÃ¡ng lÃ  thá»© 6 tuáº§n trÆ°á»›c thÃ¬ pháº£i, áº£nh máº·c white long tee vá»›i shorts tráº¯ng. Cool ngáº§u láº¯m cÆ¡. Uáº§y ai tÃ¬m Ä‘Æ°á»£c em háº­u táº¡ kem chuá»‘i nhÃ© :D ---- #4: 13/11/2015 22:13:51 TÃ¬m 1 anh há»�c lá»‹ch sá»­ vÄƒn minh tháº¿ giá»›i vÃ o chÃ¬u thá»© 4 tiáº¿t 6-9 :) Ä�áº§u nÄƒm hay máº·t Ã¡o thun xanh dÆ°Æ¡ng biá»ƒn Ä‘Ã³ng thÃ¹ng vÃ  hay ngá»“i bÃ n Ä‘áº§u Tuáº§n trÆ°á»›c anh cÃ³ nghá»‰ vÃ  tuáº§n nÃ y anh Ä‘i há»�c máº·c Ã¡o thun mÃ u nÃ¢u :3 Má»�i ngÆ°á»�i giÃºp em vá»›i Em cÃ¡m Æ¡n nhiá»�u :) --- - Yun -";
			//System.out.println("Corrected: " + server.runSpellCheck(demo));
			//System.out.println("Score " + server.runAnalyzeSentiment(demo, true));
			server.start();

			FileOutputStream out=new FileOutputStream("afterCheck.txt");
			PrintWriter output=new PrintWriter(out,true);//auto flush
			//server.runAnalyzeSentiment(demo, false);
			String[] test = server.runSpellCheckAndToken(demo);
			double rs = server.runAnalyzeSentiment(demo, true);
			
			for(String a : test){
				output.println("AQ: " + rs + " ::: " + a);
			}

			out.close();
			output.close();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}
