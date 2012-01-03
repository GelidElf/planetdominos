/**
 * 
 */
package progen.output.outputers;

import progen.kernel.population.Individual;
import progen.output.HistoricalData;
import progen.output.dataCollectors.DataCollector;
import progen.output.plugins.Plugin;

/**
 * @author jirsis
 * @since 2.0
 */
public class BestIndividualFile extends FileOutput {
	
	private DataCollector data;
	
	public BestIndividualFile(){
		super("bestIndividual.txt", false);
		HistoricalData historical=HistoricalData.makeInstance();
		data=historical.getCurrentDataCollector("ExperimentIndividualData");
	}

	/* (non-Javadoc)
	 * @see progen.output.outputers.Outputer#print()
	 */
	public void print() {
		this.init();
		Plugin plugin = data.getPlugin("best");
		
		Individual individual = (Individual)plugin.getValue();
		
		for(int i=0;i<individual.getTotalADF();i++){
			super.writer.println(Formatter.tree(individual.getTrees().get("ADF"+i)));
		}
		
		for(int i=0;i<individual.getTotalRPB();i++){
			super.writer.println(Formatter.tree(individual.getTrees().get("RPB"+i)));
		}
		this.close();
	}
}
