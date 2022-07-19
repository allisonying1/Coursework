package artsPackage;

import java.util.Arrays;
import java.util.Objects;

public abstract class ArtPiecesWithDimensions extends ArtPieces {

  protected static final Integer STORE_WIDTH = 0;
  protected static final Integer STORE_HEIGHT = 1;
  protected static final Integer NUMBER_OF_DIMENSION_INPUTS = 2;
  protected static final Integer STARTING_BID_WIDTH_THRESHOLD = 12;
  protected static final Integer STARTING_BID_HEIGHT_THRESHOLD = 16;
  protected static final Double LARGE_PAINTING_FACTOR = 1.2;

  protected Double width;
  protected Double height;
  protected Double[] dimensions = new Double[NUMBER_OF_DIMENSION_INPUTS];

  public ArtPiecesWithDimensions(String name, Name[] creators,
      Name[] owners, Double latestAskingPrice, Double width, Double height) {
    super(name, creators, owners, latestAskingPrice);
    this.width = width;
    this.height = height;
    this.dimensions[STORE_WIDTH] = width;
    this.dimensions[STORE_HEIGHT] = height;
  }

  @Override
  public Double calculateStartingBid() {
    if (this.width > STARTING_BID_WIDTH_THRESHOLD && this.height > STARTING_BID_HEIGHT_THRESHOLD) {
      return super.calculateStartingBid() * LARGE_PAINTING_FACTOR;
    }
    return super.calculateStartingBid();
  }

  public Double getWidth() {
    return width;
  }

  public Double getHeight() {
    return height;
  }

  public Double[] getDimensions() {
    return dimensions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArtPiecesWithDimensions)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    ArtPiecesWithDimensions that = (ArtPiecesWithDimensions) o;
    return Objects.equals(getWidth(), that.getWidth()) && Objects.equals(
        getHeight(), that.getHeight()) && Arrays.equals(getDimensions(),
        that.getDimensions());
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), getWidth(), getHeight());
    result = 31 * result + Arrays.hashCode(getDimensions());
    return result;
  }

  @Override
  public String toString() {
    return "ArtPiecesWithDimensions{" +
        "width=" + width +
        ", height=" + height +
        ", dimensions=" + Arrays.toString(dimensions) +
        "} " + super.toString();
  }
}
