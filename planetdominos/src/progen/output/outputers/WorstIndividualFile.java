/**
 * 
 */
package progen.output.outputers;

import java.io.File;

import progen.context.ProGenContext;
import progen.kernel.population.Individual;
import progen.output.HistoricalData;
import progen.output.dataCollectors.DataCollector;
import progen.output.plugins.Plugin;

/**
 * @author jirsis
 * @since 2.0
 */
public class WorstIndividualFile extends FileOutput {
	
	private DataCollector data;
	
	private boolean finish;
	
	public WorstIndividualFile(){
		super(ProGenContext.getMandatoryProperty("progen.output.experiment")+File.pathSeparator+"bestIndividual.txt", false);
		HistoricalData historical=HistoricalData.makeInstance();
		data=historical.getCurrentDataCollector("ExperimentIndividualData");
		finish=false;
	}

	/* (non-Javadoc)
	 * @see progen.output.outputers.Outputer#print()
	 */
	public void print() {
		if(finish){
			Plugin plugin = data.getPlugin("worst");
			
			Individual individual = (Individual)plugin.getValue();
			
			for(int i=0;i<individual.getTotalADF();i++){
				super.writer.println(Formatter.tree(individual.getTrees().get("ADF"+i)));
			}
			
			for(int i=0;i<individual.getTotalRPB();i++){
				super.writer.println(Formatter.tree(individual.getTrees().get("RPB"+i)));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see progen.output.outputers.Outputer#activateFinishOutput()
	 */
	public void activateFinishOutput() {
		finish=true;
	}

}
