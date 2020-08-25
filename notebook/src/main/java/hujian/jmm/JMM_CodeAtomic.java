package hujian.jmm;

public class JMM_CodeAtomic {
	public static Integer counter = 0;
	public static void main(String[] args) {
		for(int i = 0 ;i<10;i++) {
			Thread ta = new Thread(()->{
				for(int j=0;j<1000;j++) {
					counter++;
				}
					
			});
			ta.start();
		}
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(counter);
		 
	}
	
}
