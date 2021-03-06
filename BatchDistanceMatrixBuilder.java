import java.util.ArrayList;
import java.util.List;

public class BatchDistanceMatrixBuilder {

    // n-sqaure size (2500 actual)
    private int boxLength;

    BatchDistanceMatrixBuilder(int boxLength) {
        this.boxLength = boxLength;
    }

    //write batch methods for generation

    //method to get pseudo calculated value mimicking distance between points
    public static int getVal(int posi, int posj, int i, int j) {
        return    i*j;
    }

    public int[][] solve(List<Integer> list){
		int listSize = list.size();
		int[][] box = new int[listSize][listSize];
		int boxSize = boxLength*boxLength;
		int callsToFillBoxLine = (listSize/boxLength);
		int remainingColumns = listSize%boxLength;
		int callsForOneExcessFill = listSize/boxSize;
		int excessFillStart = listSize - listSize%boxLength;
		int excessFillLastCallIndex = listSize-listSize%boxSize;
		
		if(boxLength>=listSize){
		    fillBox(list, list, box, 0, 0, listSize);
		}
		else{
			for(int i = 0; i < callsToFillBoxLine; i++){
				List<Integer> origins = new ArrayList<>();
				for(int k = 0; k < boxLength; k++){
					origins.add(list.get(boxLength*i+k));
				}
				for(int j = 0; j < callsToFillBoxLine; j++){
					List<Integer> destinations = new ArrayList<>();
					for(int k = 0; k < boxLength; k++){
						destinations.add(list.get(boxLength*j+k));
					}
					fillBox(origins, destinations, box, i, j, boxLength);
				}
					
			}
			for(int j = excessFillStart; j <excessFillStart + remainingColumns; j++){
				List<Integer> destinations = new ArrayList<>();
				destinations.add(list.get(j));
				if(callsForOneExcessFill == 0){
					List<Integer> origins = new ArrayList<>();
					for(int i = 0; i < excessFillStart; i++){
						origins.add(list.get(i));
					}
					fillColumns(origins,destinations,j,box,0,excessFillStart);
				}
				else{
					for(int i = 0; i < callsForOneExcessFill; i++){
						List<Integer> origins = new ArrayList<>();
						for(int k = 0; k < boxSize; k++){
							origins.add(list.get(boxSize*i+k));
						}
						fillColumns(origins,destinations,j,box,i*boxSize,boxSize);
					}
					List<Integer> origins = new ArrayList<>();
					for(int k = excessFillLastCallIndex; k < excessFillStart; k++){
						origins.add(list.get(k));
					}
					fillColumns(origins, destinations, j, box, excessFillLastCallIndex,excessFillStart-excessFillLastCallIndex);
				}
			}

			for(int i = excessFillStart; i < excessFillStart + remainingColumns; i++){
				List<Integer> origins = new ArrayList<>();
				origins.add(list.get(i));
				if(callsForOneExcessFill == 0){
					List<Integer> destinations = new ArrayList<>();
					for(int j = 0; j < excessFillStart; j++){
						destinations.add(list.get(j));
					}
					fillRows(origins,destinations,i,box,0,excessFillStart);
				}
				else{
					for(int j = 0; j < callsForOneExcessFill; j++){
						List<Integer> destinations = new ArrayList<>();
						for(int k = 0; k < boxSize; k++){
							destinations.add(list.get(boxSize*j+k));
						}
						fillRows(origins,destinations,i,box,j*boxSize,boxSize);
					}
					List<Integer> destinations = new ArrayList<>();
					for(int k = excessFillLastCallIndex; k < excessFillStart; k++){
						destinations.add(list.get(k));
					}
					fillRows(origins, destinations, i, box, excessFillLastCallIndex,excessFillStart-excessFillLastCallIndex);
				}
			}

			List<Integer> origins = new ArrayList<>();
			List<Integer> destinations = new ArrayList<>();
			for(int i = excessFillStart; i <excessFillStart + remainingColumns; i++){
				
				origins.add(list.get(i));
				destinations.add(list.get(i));
			}
			fillLastBox(origins, destinations, box, excessFillStart, remainingColumns);
		}
        return box;
    }

    public static void fillBox(List<Integer> origins, List<Integer> destinations, int[][] box, int boxRow, int boxColumn, int boxLength){
	    int k = 0;
	    for(int i = boxRow*boxLength; i < boxRow*boxLength+boxLength ; i++){
	        int l = 0;
	        for(int j = boxColumn*boxLength; j < boxColumn*boxLength+boxLength ; j++){
	            box[i][j] = getVal(i, j, origins.get(k),destinations.get(l));
	            l++;
	        }
	        k++;
	    }
	}
	
	public static void fillColumns(List<Integer> origins, List<Integer> destinations, int destination, int[][] box, int fillRow, int boxSize){
	    int k = 0;
	    for (int i = fillRow; i < fillRow + boxSize; i++){
	        box[i][destination] = origins.get(k)*destinations.get(0);
	        k++;
	    }
	    
	}
	
	public static void fillRows(List<Integer> origins, List<Integer> destinations, int origin, int[][] box, int fillColumn, int boxSize){
	    int k = 0;
	    for (int i = fillColumn; i < fillColumn + boxSize; i++){
	        box[origin][i] = origins.get(0)*destinations.get(k);
	        k++;
	    }
	    
	}
	
    public static void	fillLastBox(List<Integer> origins, List<Integer> destinations, int[][] box, int excessFillStart, int remainingColumns){
        int k = 0;
        for(int i = excessFillStart; i < excessFillStart+remainingColumns ; i++){
	        int l = 0;
	        for(int j = excessFillStart; j < excessFillStart+remainingColumns ; j++){
	            box[i][j] = getVal(i,j,origins.get(k),destinations.get(l));
	            l++;
	        }
	        k++;
	    }
    }
}