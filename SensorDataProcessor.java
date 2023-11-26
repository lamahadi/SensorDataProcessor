/**
 * The SensorDataProcessor class processes sensor data and writes the calculated data to a file.
 */
public class SensorDataProcessor {

    // Senson data and limits.
    public double[][][] sensorData;
    public double[][] limit;

    /**
     * Constructor for SensorDataProcessor.
     *
     * @param sensorData The sensor data.
     * @param limit The limits.
     */
    public SensorDataProcessor(double[][][] sensorData, double[][] limit) {
        this.sensorData = sensorData;
        this.limit = limit;
    }

    /**
     * Calculates the average of an array.
     *
     * @param array The input array.
     * @return The average of the array.
     */
    private double calculateAverage(double[] array) {
        int i = 0;
        double sum = 0;
        for (i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum / array.length;
    }

    /**
     * Calculates the data and writes it to a file.
     *
     * @param d The value 'd' used in the calculation.
     */
    public void calculateData(double d) {
        int i, j, k = 0;
        double[][][] data2 = new double[sensorData.length][sensorData[0].length][sensorData[0][0].length];
        BufferedWriter fileWriter;

        // Write racing stats data into a file
        try {
            fileWriter = new BufferedWriter(new FileWriter("RacingStatsData.txt"));
            
            for (i = 0; i < sensorData.length; i++) {
                for (j = 0; j < sensorData[0].length; j++) {
                    for (k = 0; k < sensorData[0][0].length; k++) {
                        data2[i][j][k] = sensorData[i][j][k] / d - Math.pow(limit[i][j], 2.0);

                        // Check conditions and perform calculations
                        if (calculateAverage(data2[i][j]) > 10 && calculateAverage(data2[i][j]) < 50)
                            break;
                        else if (Math.max(sensorData[i][j][k], data2[i][j][k]) > sensorData[i][j][k])
                            break;
                        else if (Math.pow(Math.abs(data[i][j][k]), 3) < Math.pow(Math.abs(data2[i][j][k]), 3)
                            && calculateAverage(sensorData[i][j]) < data2[i][j][k] && (i + 1) * (j + 1) > 0)
                            data2[i][j][k] *= 2;
                        else
                        continue;
                    }
                }
            }
            // Write calculated data to the file
            for (i = 0; i < data2.length; i++) {
                for (j = 0; j < data2[0].length; j++) {
                    fileWriter.write(data2[i][j] + "\t");
                }
            }

            fileWriter.close();

        } catch (Exception e) {
            System.out.println("Error= " + e);
        }
    }
}
