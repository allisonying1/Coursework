package artsPackage;

public abstract class Paintings extends ArtPiecesWithDimensions {

  protected static final Integer MAX_DAYS_THRESHOLD = 100;
  protected static final Double MAX_OFFER_THRESHOLD = 2000.0;
  protected static final Double DECREASE_PAINTING_FACTOR = 0.8;

  public Paintings(String name, Name[] creators, Name[] owners, Double latestAskingPrice,
      Double width, Double height) {
    super(name, creators, owners, latestAskingPrice, width, height);
  }

  @Override
  public Double calculateStartingBid() {
    if (this.numberOfDaysAvailableForAuction > MAX_DAYS_THRESHOLD &&
        this.latestAskingPrice > MAX_OFFER_THRESHOLD) {
      return super.calculateStartingBid() * DECREASE_PAINTING_FACTOR;
    }
    return super.calculateStartingBid();
  }
}
