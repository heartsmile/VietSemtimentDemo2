package main;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;

import app.server.handling.ServerImpl;

public class ExtractOpinion {

	public static void main(String[] args) {

		try {
			ServerImpl server = new ServerImpl();
			
			String demo = "#NV_Cfs5204  :(\n --- #1: 13/11/2015 20:29:13 Form trai Bách Khoa: bạn nam nào đó ở trường nv hay đi xe bus số 8 ơi, cao ốm hay đội mũ, bạn nhìn thụ quá mình làm công của bạn dc ko hihi --- #2: 13/11/2015 21:20:14 Hello, It's me How are you? Anh bị mất bóp nhưng điều anh lo sợ nhất chính là mất hình em anh để trong đó. Anh bị hư điện thoại điều anh lo sợ nhất là khi sửa xong thì mất hết tin nhắn và hình của em trong máy Nhưng thật may mắn là anh vẫn giữ được những thứ quý giá đó :( Lương Trường Giang ơi còn nhớ anh không, bỏ chặn fb và ib anh nhé :( Mình học Điện nên viết không hay mong các bạn bỏ qua :(  --- #3: 13/11/2015 21:52:24 Vì confession hỏi danh tính anh tóc củ tỏi lần trước của em có bạn comment bảo trường nhiều củ tỏi lắm nói thế sao tìm được, nên em quyết định soạn lại. Thật ra đi ngang ảnh mấy lần nhưng em cũng chỉ nhìn mỗi tóc củ tỏi thôi nên không biết miêu tả sao. Chỉ nhớ mang máng là thứ 6 tuần trước thì phải, ảnh mặc white long tee với shorts trắng. Cool ngầu lắm cơ. Uầy ai tìm được em hậu tạ kem chuối nhé :D ---- #4: 13/11/2015 22:13:51 Tìm 1 anh học lịch sử văn minh thế giới vào chìu thứ 4 tiết 6-9 :) Đầu năm hay mặt áo thun xanh dương biển đóng thùng và hay ngồi bàn đầu Tuần trước anh có nghỉ và tuần này anh đi học mặc áo thun màu nâu :3 Mọi người giúp em với Em cám ơn nhiều :) --- - Yun -";
			//System.out.println("Corrected: " + server.runSpellCheck(demo));
			//System.out.println("Score " + server.runAnalyzeSentiment(demo, true));
			server.start();
/*
			FileOutputStream out=new FileOutputStream("afterCheck.txt");
			PrintWriter output=new PrintWriter(out,true);//auto flush
			//server.runAnalyzeSentiment(demo, false);
			String[] test = server.runSpellCheckAndToken(demo);
			double rs = server.runAnalyzeSentiment(demo, true);
			
			for(String a : test){
				output.println("QuanTran: " + rs + " ::: " + a);
			}

			out.close();
			output.close();*/
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
