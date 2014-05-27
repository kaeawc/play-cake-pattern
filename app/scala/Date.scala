package scala

object Date {

  type Date = java.util.Date

  type DateTime = org.joda.time.DateTime

  import java.util.TimeZone

  import java.text.SimpleDateFormat

  val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

  def now = new DateTime(utc(new Date))

  def utc(date:Date) = {
    val tz = TimeZone.getDefault()
    var ret = new Date( date.getTime() - tz.getRawOffset() )

    // if we are now in DST, back off by the delta.  Note that we are checking the GMT date, this is the KEY.
    if ( tz.inDaylightTime( ret )){
      val dstDate = new Date( ret.getTime() - tz.getDSTSavings() )

      // check to make sure we have not crossed back into standard time
      // this happens when we are on the cusp of DST (7pm the day before the change for PDT)
      if ( tz.inDaylightTime( dstDate )){
        ret = dstDate
      }
    }

    ret
  }

  def asDateTime(date:Date) =
    new DateTime(date)

  def asDateTime(date:Option[Date]) =
    if(date.isDefined)
      Some(new DateTime(date.get))
    else
      None

  def asDate(date:DateTime) =
    date.toDate

  def asDate(date:Option[DateTime]) =
    if(date.isDefined)
      Some(date.get.toDate)
    else
      None
}
