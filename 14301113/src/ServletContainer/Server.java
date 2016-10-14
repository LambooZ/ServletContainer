package ServletContainer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Server {
	static ServletRequest req = null;
	static ServletResponse res = null;
	
	public static void main(String args[]) throws Exception{
		final int port = 8888; 
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(port);
			println("���������ڼ��Ӷ˿ڣ� " + serverSocket.getLocalPort());
			
			while(true){
				Socket clientSocket = serverSocket.accept();
				//println("��������ͻ���һ���µ�TCP���ӣ��ÿͻ��ĵ�ַΪ��" + clientSocket.getInetAddress() + ": " + clientSocket.getPort());
				
				service(clientSocket);
				clientSocket.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void service(Socket socket) throws Exception{
		try {
			InputStream socketIn = socket.getInputStream();
			//Thread.sleep(500);
			req = new MyServletRequest(socketIn);
			//println("���ڴ���ServletRequest����");
			OutputStream socketOut = socket.getOutputStream();
			res = new MyServletResponse(socketOut);
			//println("���ڴ���ServletResponse����");
			ServletProcessor.processServletRequest((MyServletRequest)req, (MyServletResponse)res); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void println(String content){
		System.out.println(content);
	}
}
