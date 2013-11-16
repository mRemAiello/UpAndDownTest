import java.util.Random;
import org.apache.commons.math3.stat.inference.ChiSquareTest;


public class UpAndDownTest
{
	private double[] nRandom;
	private int[] iSequence;
	private int[] iCalcSequence;
	private double[] valoriAttesi;
	private long[] iMaxSequence;
	
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
	
	public UpAndDownTest(int n)
	{
		generaNumeriRand(n);
		generaZeroUno(n);
		contaRunUp(n);
		contaValoriAttesi(n);
		
		boolean res1 = testChiQuadro(0.05D);
		boolean res2 = testChiQuadro(0.01D);
		
		System.out.print("Test Chi quadro con alpha 0.05 : ");
		if(res1)
			System.out.println("Passato");
		else System.out.println("Non passato");
		
		System.out.print("Test Chi quadro con alpha 0.01 : ");
		if(res2)
			System.out.println("Passato");
		else System.out.println("Non passato");
		
		System.out.println("------------------------");
	}

	private void generaNumeriRand(int N)
	{
		System.out.println("Test Iniziato!");
		
		nRandom = new double[N];
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
	}
	
	private void generaZeroUno(int N)
	{
		iSequence = new int[N];
		for(int i=0; i<N - 1; i++)
			iSequence[i] = (nRandom[i] < nRandom[i+1]) ? 1 : 0;
		
		System.out.print("Sequenza run up/run down: ");
		for(int i=0; i<N; i++)
			System.out.print(iSequence[i]);
	}
	
	private void contaRunUp(int N)
	{
		iCalcSequence = new int[N];
		int i = 0;
		int j = 0;
		while(i < N)
		{
			double lastNumber = iSequence[i];
			while(i < N && lastNumber == iSequence[i])
			{
				i++;
				j++;
			}
			iCalcSequence[j] = iCalcSequence[j] + 1;
			j = 0;
		}
	}
	
	private void contaValoriAttesi(int N)
	{
		int max = N - 1;
		while(iCalcSequence[max] == 0)
			max--;
		
		valoriAttesi = new double[max];
		for(int i=0; i<max; i++)
			valoriAttesi[i] = arrotonda(calculatesFormulas(i,N), 2);
		
		iMaxSequence = new long[max];
		for(int i=0; i<max; i++)
			iMaxSequence[i] = iCalcSequence[i + 1];
		
		System.out.println();
		for(int i=0; i<max; i++)
		{
			System.out.println("N° run di lunghezza "+(i+1)+": "+iMaxSequence[i]);
			System.out.println("N° run atteso di lunghezza "+(i+1)+": "+valoriAttesi[i]);
		}
	}
		
	public boolean testChiQuadro(double alpha)
	{
		long[][] counts = new long[valoriAttesi.length][2];
		for(int i=0; i<valoriAttesi.length; i++)
		{
			counts[i][0] = iMaxSequence[i];
			counts[i][1] = Math.round(valoriAttesi[i]);
		}
		
		ChiSquareTest chi = new ChiSquareTest();
		return chi.chiSquareTest(counts, alpha);
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
		else if(k < N - 1) {
			double kquadrato = Math.pow(k, 2);
			double kcubo = Math.pow(k, 3);
			double fact = facts[k+3];
			double result = (kquadrato + (3*k) + 1) * (double)(N);
			result -= kcubo + (3*k) - k - 4;
			result *= 2;
			result /= fact;
			return result;
		}
		else return 2.0D / ((double) facts[N]);
	}
}
