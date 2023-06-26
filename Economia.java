import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import static java.lang.System.out;
import static java.lang.System.err;

public class Economia{

	public static Character printQ(String question, ArrayList<String> ans, int number){
		if(question == null){
			return null;
		}

		out.println("Q" + (number + 1) + ")  " + question);
		Collections.shuffle(ans);

		int res = -1;
		for(int i = 0; i < ans.size(); i++){
			out.println((char)('A' + i) + ")  "+ ans.get(i).substring(2));

			if(ans.get(i).startsWith("V:")){
				res = i;
			}
		}
		out.println();
		return res == -1 ? '?' : (char)('A' + res);
	}

	public static void printA(ArrayList<Character> res){
		out.print("\n\n\n\n\n\n\n\n\n\n");

		for(int i = 0; i < res.size(); i++){
			out.println("Q" + (i + 1) + "  \t|  " + res.get(i));
			if(i != res.size() - 1){
				out.println("------------");
			}
		}
	}

	public static void main(String args[]) throws Exception{
		Scanner sc = new Scanner(new File("economia.txt"));

		ArrayList<Character> responses = new ArrayList<>();
		ArrayList<String> answers = null;
		String question = null;
		int count = -1, cRes = -1;
		Character tmp;

		while(sc.hasNext()){
			String line = sc.nextLine().trim();

			if(line.startsWith("C:")){
				out.println("--------------------------------------------------");
				out.println("--------------------- " + line + " ---------------------");
				out.println("--------------------------------------------------");
				out.println();
			}else if(line.isBlank()){
			}else if(line.startsWith("Q:")){
				if((tmp = printQ(question, answers, count)) != null)
					responses.add(count, tmp);

				count++;
				cRes = -1;
				question = line.substring(2);
				answers = new ArrayList<>();
			}else if(question == null){
				err.println("ERROR: question not found at line:" + line);
				return;
			}else if(line.startsWith("V:")){
				answers.add(++cRes, line);
				//TODO
			}else if(line.startsWith("F:")){
				answers.add(++cRes, line);
			}else if(cRes == -1){
				question += " " + line;
			}else{
				answers.set(cRes, answers.get(cRes) + " " + line);
			}
		}

		if((tmp = printQ(question, answers, count)) != null)
			responses.add(count, tmp);
		printA(responses);
	}
}