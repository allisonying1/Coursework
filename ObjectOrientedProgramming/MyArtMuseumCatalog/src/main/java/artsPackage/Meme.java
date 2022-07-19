package artsPackage;

public class Meme extends NonFungibleTokens {

  protected static final Integer AUCTION_DAY_THRESHOLD = 15;
  protected static final Double MEME_DECREASE_FACTOR = 0.7;

  public Meme(String name, Name[] creators, Name[] owners, Double latestAskingPrice,
      String tokenStandard) {
    super(name, creators, owners, latestAskingPrice, tokenStandard);
  }

  @Override
  public Double calculateStartingBid() {
    if (this.numberOfDaysAvailableForAuction > AUCTION_DAY_THRESHOLD) {
      return super.calculateStartingBid() * MEME_DECREASE_FACTOR;
    }
    return super.calculateStartingBid();
  }
}
