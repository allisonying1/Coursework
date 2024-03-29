package generatorPackage;

/**
 * An Interface for Command Processor
 */

public interface ICommandProcessor{

    /**
     * take in what was written in the command line and populate command and parameter
     * @param cmdline the string from main
     * @throws InvalidArgumentException is thrown when the input violates rules for arguments
     */
    public void processArgs(String[] cmdline) throws InvalidArgumentException ;



}
