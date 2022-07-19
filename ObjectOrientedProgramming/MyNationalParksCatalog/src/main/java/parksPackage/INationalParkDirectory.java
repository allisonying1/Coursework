package parksPackage;

import java.time.LocalDate;

/**
 * An IMMUTABLE National Park Directory Data Collection for a National Park Directory System
 */
public interface INationalParkDirectory {

  /**
   * Count the number of National Parks in the National Park Directory
   *
   * @return number of National Parks as an Integer
   */
  Integer count();

  /**
   * Check if the specified National Park is included in the National Park Directory
   *
   * @param nationalPark as a National Park Object
   * @return if National Park is included in the directory, as a Boolean
   */
  Boolean contains(NationalPark nationalPark);

  /**
   * Add a new National Park to the end of the National Park Directory
   *
   * @param nationalPark as a National Park Object
   * @return New National Park Directory as a National Park Directory object
   */
  INationalParkDirectory add(NationalPark nationalPark);

  /**
   * Modify National Park ID in the National Park Directory
   *
   * @param nationalPark as a National Park Object
   * @param id           of the National Park, as a String.
   * @return New National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  INationalParkDirectory modifyID(NationalPark nationalPark, String id)
      throws NationalParkNotFoundException;

  /**
   * Modify National Park Name in the National Park Directory
   *
   * @param nationalPark as a National Park Object
   * @param name         of the National Park, as a String.
   * @return New National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  INationalParkDirectory modifyName(NationalPark nationalPark, String name)
      throws NationalParkNotFoundException;

  /**
   * Modify National Park's state location in the National Park Directory
   *
   * @param nationalPark as a National Park Object
   * @param state        of the National Park, as a String.
   * @return New National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  INationalParkDirectory modifyState(NationalPark nationalPark, String state)
      throws NationalParkNotFoundException;

  /**
   * Modify National Park's total area in the National Park Directory
   *
   * @param nationalPark as a National Park Object
   * @param area         of the National Park, as a Double.
   * @return New National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  INationalParkDirectory modifyArea(NationalPark nationalPark, Double area)
      throws NationalParkNotFoundException;

  /**
   * Modify National Park's founding date in the National Park Directory
   *
   * @param nationalPark    as a National Park Object
   * @param dateParkFounded of the National Park, as a LocalDate object.
   * @return New National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  INationalParkDirectory modifyDateFound(NationalPark nationalPark, LocalDate dateParkFounded)
      throws NationalParkNotFoundException;

  /**
   * Modify National Park's visitor centers in the National Park Directory
   *
   * @param nationalPark   as a National Park Object
   * @param visitorCenters of the National Park, as an array of Strings.
   * @return New National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  INationalParkDirectory modifyVisitorCenters(NationalPark nationalPark, String[] visitorCenters)
      throws NationalParkNotFoundException;

  /**
   * Modify National Park's open year round information in the National Park Directory
   *
   * @param nationalPark  as a National Park Object
   * @param openYearRound information of the National Park, as a Boolean.
   * @return New National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  INationalParkDirectory modifyOpenYearRound(NationalPark nationalPark, Boolean openYearRound)
      throws NationalParkNotFoundException;

  /**
   * Remove a specified National Park from the National Park directory
   *
   * @param nationalPark as a National Park Object
   * @return National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  INationalParkDirectory remove(NationalPark nationalPark) throws NationalParkNotFoundException;

  /**
   * Find and return the first National Parks from the Directory that is located within the
   * specified state
   *
   * @param state as a String.
   * @return National Park as a National Park object
   */
  NationalPark findInState(String state);

  /**
   * Find and return all the National Parks from the Directory that is located within the specified
   * state
   *
   * @param state as a String.
   * @return National Parks as an array of National Park objects.
   */
  NationalPark[] findAllInState(String state);

  /**
   * Find and return all the National Parks from the Directory that is open all year round.
   *
   * @return National Parks as an array of National Park objects.
   */
  NationalPark[] findAllYearRound();

  /**
   * Get a National Park item from the directory based on its unique ID
   *
   * @param id of the national park as a String.
   * @return National Park as a National Park Object
   * @throws InvalidNationalParkIDException if ID is not associated with any of the Parks in our
   *                                        directory
   */
  NationalPark get(String id) throws InvalidNationalParkIDException;

}
