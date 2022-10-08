import { memo } from 'react';
import { useRecoilValue } from 'recoil';
import { getMemberInfoState } from '../../recoil/selectors/getMemberInfoState';
import moment from 'moment';
import CalendarApp from 'react-calendar';
import './Calendar.css';

export default memo(function Calendar({ summary }) {
  const records = useRecoilValue(getMemberInfoState).records;

  return (
    <section className="flex justify-center items-center sm:my-5 h-full">
      <CalendarApp
        showNeighboringMonth={false}
        formatDay={(locale, date) => (summary ? '' : moment(date).format('DD'))}
        tileContent={({ date }) =>
          records.length === 0
            ? null
            : records.map((el) =>
                el.date === moment(date).format('YYYY-MM-DD') ? (
                  <div
                    className={
                      el.record < 900
                        ? 'record record__mint'
                        : el.record < 1800
                        ? 'record record__light-green'
                        : el.record < 3600
                        ? 'record record__green'
                        : 'record record__deep-green'
                    }
                  >
                    {summary
                      ? ''
                      : el.record < 60
                      ? '0분'
                      : el.record >= 60
                      ? `${Math.floor(el.record / 60)}분`
                      : ''}
                  </div>
                ) : (
                  ''
                )
              )
        }
      ></CalendarApp>
    </section>
  );
});
