import java.util.Random;

import org.apache.commons.math3.stat.inference.ChiSquareTest;


public class UpAndDownTest
{
	private int N = 50;
	private int[] facts = {
		1,
		1,
		2,
		6,
		24,
		120,
		720,
		5040,
		5040,
		40320,
		362880
	};
	
	public UpAndDownTest(int n) {
		N = n;
	}

	public UpAndDownTest() {
	}

	public void makeTest()
	{
		System.out.println("Test Iniziato!");
		
		double[] nRandom = new double[N];
		Random rdm = new Random();
		for(int i=0; i<N; i++)
		{
			double rdmN;
			do {
				rdmN = arrotonda(rdm.nextDouble(), 1);
			} while(rdmN == 0.0d);
			nRandom[i] = rdmN;
		}
		
		System.out.print("[");
		for(int i = 0, j = 0; i<N - 1; i++)
		{
			if(i > (j*15) + 15)
			{
				System.out.println();
				j++;
			}
			System.out.print(nRandom[i]+", ");
		}
		System.out.println(nRandom[N - 1]+ "]");
		
		System.out.println("------------------------");
		
		int[] iSequence = new int[N];
		for(int i=0; i<N - 1; i++)
			iSequence[i] = (nRandom[i] < nRandom[i+1]) ? 1 : 0;
		
		System.out.print("Sequenza run up/run down: ");
		for(int i=0; i<N; i++)
			System.out.print(iSequence[i]);
		
		int j = 0;
		int[] iCalcSequence = new int[N];
		for(int i=0; i<N;)
		{
			double lastNumber = iSequence[i];
			while(i < N && lastNumber == iSequence[i])
			{
				iCalcSequence[j]++;
				i++;
			}
			j++;
		}
		
		int max = 0;
		for(int i=0; i<iCalcSequence.length; i++)
			if(max < iCalcSequence[i])
				max = iCalcSequence[i];
		
		long[] iMaxSequence = new long[max+1];
		for(int i=0; i<iCalcSequence.length; i++)
			iMaxSequence[iCalcSequence[i]]++;
		
		double[] valoriAttesi = new double[max+1];
		for(int i=0; i<valoriAttesi.length; i++)
			valoriAttesi[i] = arrotonda(calculatesFormulas(i,N), 2);
		
		System.out.println();
		for(int i=0; i<iMaxSequence.length; i++)
		{
			System.out.println("N° run di lunghezza "+(i+1)+": "+iMaxSequence[i]);
			System.out.println("N° run atteso di lunghezza "+(i+1)+": "+valoriAttesi[i]);
		}
		
		long[][] counts = new long[valoriAttesi.length][2];
		for(int i=0; i<valoriAttesi.length; i++)
		{
			counts[i][0] = iMaxSequence[i];
			counts[i][1] = Math.round(valoriAttesi[i]);
		}
		
		ChiSquareTest chi = new ChiSquareTest();
		
		System.out.println("------------------------");
		System.out.println("Test Chi quadro con alpha 0.05 : "+chi.chiSquareTest(counts, 0.05));
		System.out.println("Test Chi quadro con alpha 0.01 : "+chi.chiSquareTest(counts, 0.01)+"\n");
	}

	private double arrotonda(double d, int p) 
	{
		return Math.rint(d*Math.pow(10,p))/Math.pow(10,p);
	}
	
	private double calculatesFormulas(int k, int N)
	{
		if(k == 0)
			return (double)(N+1) / 12;
		else if(k == 1)
			return (double)(11 * N - 14) / 12;
		else {
			double kquadrato = Math.pow(k, 2);
			double kcubo = Math.pow(k, 3);
			double fact = facts[k+3];
			double result = (kquadrato + (3*k) + 1) * (double)(N);
			result -= kcubo + (3*k) - k - 4;
			result *= 2;
			result /= fact;
			return result;
		}
	}
}
