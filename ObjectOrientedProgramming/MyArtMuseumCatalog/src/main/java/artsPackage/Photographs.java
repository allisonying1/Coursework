package artsPackage;

import java.util.Objects;

public abstract class Photographs extends ArtPiecesWithDimensions {

  protected String makeOfCamera;
  protected String modelOfCamera;
  protected static final Double CAMERA_FACTOR = 1.35;

  public Photographs(String name, Name[] creators, Name[] owners,
      Double latestAskingPrice, Double width, Double height, String makeOfCamera,
      String modelOfCamera) {
    super(name, creators, owners, latestAskingPrice, width, height);
    this.makeOfCamera = makeOfCamera;
    this.modelOfCamera = modelOfCamera;
  }

  @Override
  public Double calculateStartingBid() {
    if (this.makeOfCamera.equals("Canon") && this.modelOfCamera.equals("EOS R5")) {
      return super.calculateStartingBid() * CAMERA_FACTOR;
    }
    return super.calculateStartingBid();
  }

  public String getMakeOfCamera() {
    return makeOfCamera;
  }

  public String getModelOfCamera() {
    return modelOfCamera;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Photographs)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Photographs that = (Photographs) o;
    return Objects.equals(getMakeOfCamera(), that.getMakeOfCamera())
        && Objects.equals(getModelOfCamera(), that.getModelOfCamera());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getMakeOfCamera(), getModelOfCamera());
  }

  @Override
  public String toString() {
    return "Photographs{" +
        "makeOfCamera='" + makeOfCamera + '\'' +
        ", modelOfCamera='" + modelOfCamera + '\'' +
        "} " + super.toString();
  }
}
