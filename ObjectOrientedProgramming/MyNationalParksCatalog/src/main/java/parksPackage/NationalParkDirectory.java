package parksPackage;

import java.time.LocalDate;
import java.util.Arrays;

public class NationalParkDirectory implements INationalParkDirectory {

  private final NationalPark[] collection;

  /**
   * A National Park Directory class is an Immutable Collection of National Park Objects
   *
   * @param collection of National Parks in the system, stored as an array of National Park objects
   */

  public NationalParkDirectory(NationalPark[] collection) {
    this.collection = collection;
  }

  public NationalParkDirectory() {
    this.collection = new NationalPark[0];
  }

  /**
   * getter method for the directory in the National Park Directory
   *
   * @return directory as an array of National Park objects.
   */
  private NationalPark[] getCollection() {
    return collection;
  }

  /**
   * Count the number of National Parks in the National Park Directory
   *
   * @return number of National Parks as an Integer
   */
  @Override
  public Integer count() {
    return this.collection.length;
  }

  /**
   * Check if the specified National Park is included in the National Park Directory
   *
   * @param nationalPark as a National Park Object
   * @return if National Park is included in the directory, as a Boolean
   */
  @Override
  public Boolean contains(NationalPark nationalPark) {
    for (NationalPark park : this.collection) {
      if (park.equals(nationalPark)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Add a new National Park to the end of the National Park Directory
   *
   * @param nationalPark as a National Park Object
   * @return New National Park Directory as a National Park Directory object
   */
  @Override
  public NationalParkDirectory add(NationalPark nationalPark) {
    if (this.contains(nationalPark)) {
      return this;
    }

    NationalPark[] copied = new NationalPark[this.collection.length + 1];
    System.arraycopy(this.collection, 0, copied, 0, this.collection.length);
    copied[this.collection.length] = nationalPark;
    return new NationalParkDirectory(copied);
  }

  /**
   * Modify National Park ID in the National Park Directory
   *
   * @param nationalPark as a National Park Object
   * @param id           of the National Park, as a String.
   * @return New National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  @Override
  public NationalParkDirectory modifyID(NationalPark nationalPark, String id)
      throws NationalParkNotFoundException {
    if (!this.contains(nationalPark)) {
      throw new NationalParkNotFoundException("National Park is not in our system.");
    }
    NationalParkDirectory modifications = this;
    for (int i = 0; i < this.collection.length; ++i) {
      if (this.collection[i].equals(nationalPark)) {
        modifications.getCollection()[i] = new NationalPark(id,
            this.collection[i].getNationalParkName(), this.collection[i].getState(),
            this.collection[i].getArea(), this.collection[i].getDateParkFounded(),
            this.collection[i].getVisitorCenters(), this.collection[i].getOpenYearRound());
      }
    }
    return modifications;
  }

  /**
   * Modify National Park Name in the National Park Directory
   *
   * @param nationalPark as a National Park Object
   * @param name         of the National Park, as a String.
   * @return New National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  @Override
  public NationalParkDirectory modifyName(NationalPark nationalPark, String name)
      throws NationalParkNotFoundException {
    if (!this.contains(nationalPark)) {
      throw new NationalParkNotFoundException("National Park is not in our system.");
    }
    NationalParkDirectory modifications = this;
    for (int i = 0; i < this.collection.length; ++i) {
      if (this.collection[i].equals(nationalPark)) {
        modifications.getCollection()[i] = new NationalPark(this.collection[i].getNationalParkID(),
            name, this.collection[i].getState(), this.collection[i].getArea(),
            this.collection[i].getDateParkFounded(), this.collection[i].getVisitorCenters(),
            this.collection[i].getOpenYearRound());
      }
    }
    return modifications;
  }

  /**
   * Modify National Park's state location in the National Park Directory
   *
   * @param nationalPark as a National Park Object
   * @param state        of the National Park, as a String.
   * @return New National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  @Override
  public NationalParkDirectory modifyState(NationalPark nationalPark, String state)
      throws NationalParkNotFoundException {
    if (!this.contains(nationalPark)) {
      throw new NationalParkNotFoundException("National Park is not in our system.");
    }
    NationalParkDirectory modifications = this;
    for (int i = 0; i < this.collection.length; ++i) {
      if (this.collection[i].equals(nationalPark)) {
        modifications.getCollection()[i] = new NationalPark(this.collection[i].getNationalParkID(),
            this.collection[i].getNationalParkName(), state,
            this.collection[i].getArea(), this.collection[i].getDateParkFounded(),
            this.collection[i].getVisitorCenters(), this.collection[i].getOpenYearRound());
      }
    }
    return modifications;
  }

  /**
   * Modify National Park's total area in the National Park Directory
   *
   * @param nationalPark as a National Park Object
   * @param area         of the National Park, as a Double.
   * @return New National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  @Override
  public NationalParkDirectory modifyArea(NationalPark nationalPark, Double area)
      throws NationalParkNotFoundException {
    if (!this.contains(nationalPark)) {
      throw new NationalParkNotFoundException("National Park is not in our system.");
    }
    NationalParkDirectory modifications = this;
    for (int i = 0; i < this.collection.length; ++i) {
      if (this.collection[i].equals(nationalPark)) {
        modifications.getCollection()[i] = new NationalPark(this.collection[i].getNationalParkID(),
            this.collection[i].getNationalParkName(), this.collection[i].getState(),
            area, this.collection[i].getDateParkFounded(), this.collection[i].getVisitorCenters(),
            this.collection[i].getOpenYearRound());
      }
    }
    return modifications;
  }

  /**
   * Modify National Park's founding date in the National Park Directory
   *
   * @param nationalPark    as a National Park Object
   * @param dateParkFounded of the National Park, as a LocalDate object.
   * @return New National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  @Override
  public NationalParkDirectory modifyDateFound(NationalPark nationalPark,
      LocalDate dateParkFounded) throws NationalParkNotFoundException {
    if (!this.contains(nationalPark)) {
      throw new NationalParkNotFoundException("National Park is not in our system.");
    }
    NationalParkDirectory modifications = this;
    for (int i = 0; i < this.collection.length; ++i) {
      if (this.collection[i].equals(nationalPark)) {
        modifications.getCollection()[i] = new NationalPark(this.collection[i].getNationalParkID(),
            this.collection[i].getNationalParkName(), this.collection[i].getState(),
            this.collection[i].getArea(), dateParkFounded,
            this.collection[i].getVisitorCenters(), this.collection[i].getOpenYearRound());
      }
    }
    return modifications;
  }

  /**
   * Modify National Park's visitor centers in the National Park Directory
   *
   * @param nationalPark   as a National Park Object
   * @param visitorCenters of the National Park, as an array of Strings.
   * @return New National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  @Override
  public NationalParkDirectory modifyVisitorCenters(NationalPark nationalPark,
      String[] visitorCenters) throws NationalParkNotFoundException {
    if (!this.contains(nationalPark)) {
      throw new NationalParkNotFoundException("National Park is not in our system.");
    }
    NationalParkDirectory modifications = this;
    for (int i = 0; i < this.collection.length; ++i) {
      if (this.collection[i].equals(nationalPark)) {
        modifications.getCollection()[i] = new NationalPark(this.collection[i].getNationalParkID(),
            this.collection[i].getNationalParkName(), this.collection[i].getState(),
            this.collection[i].getArea(), this.collection[i].getDateParkFounded(),
            visitorCenters, this.collection[i].getOpenYearRound());
      }
    }
    return modifications;
  }

  /**
   * Modify National Park's open year round information in the National Park Directory
   *
   * @param nationalPark  as a National Park Object
   * @param openYearRound information of the National Park, as a Boolean.
   * @return New National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  @Override
  public NationalParkDirectory modifyOpenYearRound(NationalPark nationalPark,
      Boolean openYearRound) throws NationalParkNotFoundException {
    if (!this.contains(nationalPark)) {
      throw new NationalParkNotFoundException("National Park is not in our system.");
    }
    NationalParkDirectory modifications = this;
    for (int i = 0; i < this.collection.length; ++i) {
      if (this.collection[i].equals(nationalPark)) {
        modifications.getCollection()[i] = new NationalPark(this.collection[i].getNationalParkID(),
            this.collection[i].getNationalParkName(), this.collection[i].getState(),
            this.collection[i].getArea(), this.collection[i].getDateParkFounded(),
            this.collection[i].getVisitorCenters(), openYearRound);
      }
    }
    return modifications;
  }

  /**
   * Remove a specified National Park from the National Park directory
   *
   * @param nationalPark as a National Park Object
   * @return National Park Directory as a National Park Directory object
   * @throws NationalParkNotFoundException if input National Park is not found in the directory.
   */
  @Override
  public NationalParkDirectory remove(NationalPark nationalPark)
      throws NationalParkNotFoundException {
    if (!this.contains(nationalPark) || this.collection.length == 0) {
      throw new NationalParkNotFoundException("National Park is not in our system.");
    }
    int counter = 0;
    NationalPark[] copy = new NationalPark[this.collection.length - 1];
    for (NationalPark park : this.collection) {
      if (!park.equals(nationalPark)) {
        copy[counter] = park;
        counter++;
      }
    }
    return new NationalParkDirectory(copy);
  }

  /**
   * Find and return the first National Parks from the Directory that is located within the
   * specified state
   *
   * @param state as a String.
   * @return National Park as a National Park object
   */
  @Override
  public NationalPark findInState(String state) {
    for (NationalPark nationalPark : this.collection) {
      if (nationalPark.getState().equals(state)) {
        return nationalPark;
      }
    }
    return null;
  }

  /**
   * Find and return all the National Parks from the Directory that is located within the specified
   * state
   *
   * @param state as a String.
   * @return National Parks as an array of National Park objects.
   */
  @Override
  public NationalPark[] findAllInState(String state) {
    NationalPark[] found = new NationalPark[this.collection.length];
    int index = 0;
    for (NationalPark nationalPark : this.collection) {
      if (nationalPark.getState().equals(state)) {
        found[index] = nationalPark;
        index++;
      }
    }

    NationalPark[] results = new NationalPark[index];
    System.arraycopy(found, 0, results, 0, index);
    return results;
  }

  /**
   * Find and return all the National Parks from the Directory that is open all year round.
   *
   * @return National Parks as an array of National Park objects.
   */
  @Override
  public NationalPark[] findAllYearRound() {
    NationalPark[] found = new NationalPark[this.collection.length];
    int index = 0;
    for (NationalPark nationalPark : this.collection) {
      if (nationalPark.getOpenYearRound()) {
        found[index] = nationalPark;
        index++;
      }
    }
    NationalPark[] results = new NationalPark[index];
    System.arraycopy(found, 0, results, 0, index);
    return results;
  }

  /**
   * Get a National Park item from the directory based on its unique ID
   *
   * @param id of the national park as a String.
   * @return National Park as a National Park Object
   * @throws InvalidNationalParkIDException if ID is not associated with any of the Parks in our
   *                                        directory
   */
  @Override
  public NationalPark get(String id) throws InvalidNationalParkIDException {
    if (this.collection.length == 0) {
      throw new InvalidNationalParkIDException("System is empty.");
    }
    for (NationalPark nationalPark : this.collection) {
      if (nationalPark.getNationalParkID().equals(id)) {
        return nationalPark;
      }
    }
    throw new InvalidNationalParkIDException("National Park ID is not in our system.");
  }

  /**
   * equals method for comparing an object with this National Park Directory instance
   *
   * @param o as another object
   * @return if objects match, as Boolean
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NationalParkDirectory)) {
      return false;
    }
    NationalParkDirectory that = (NationalParkDirectory) o;
    return Arrays.equals(getCollection(), that.getCollection());
  }

  /**
   * hashCode method for returning a hashCode representation of this National Park Directory
   *
   * @return hashCode as an Integer.
   */
  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + Arrays.hashCode(getCollection());
    return result;
  }

  /**
   * toString method for returning a string representation of this National Park Directory
   *
   * @return National Park Directory fields, as a string.
   */
  @Override
  public String toString() {
    return "NationalParkDirectory{" +
        "directory=" + Arrays.toString(collection) + '}';
  }
}
