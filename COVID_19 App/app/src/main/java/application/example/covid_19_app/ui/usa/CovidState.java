package application.example.covid_19_app.ui.usa;

public class CovidState {

        String mCovidState, mUSCases, mTodayUSCases, mUSDeaths, mTodayUSDeaths, mUSRecovered, mUSCritical;

        public CovidState(String mCovidState, String mUSCases) {
            this.mCovidState = mCovidState;
            this.mUSCases = mUSCases;
            this.mTodayUSCases = mTodayUSCases;
            this.mUSDeaths = mUSDeaths;
            this.mTodayUSDeaths = mTodayUSDeaths;
            this.mUSRecovered = mUSRecovered;
            this.mUSCritical = mUSCritical;
        }

        public String getmCovidState() {
            return mCovidState;
        }

        public String getmUSCases() {
            return mUSCases;
        }



}
