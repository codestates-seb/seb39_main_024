// import { useState } from 'react';
import { useRecoilValue } from 'recoil';
import { getWorkoutTimeState } from '../../recoil/selectors/getWorkoutTimeState';
import moment from 'moment';
import CalendarApp from 'react-calendar';
import './Calendar.css';

export default function Calendar() {
  const records = useRecoilValue(getWorkoutTimeState).records;
  console.log(records);

  return (
    <>
      <CalendarApp
        showNeighboringMonth={false}
        formatDay={(locale, date) => moment(date).format('DD')}
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
                    {el.record < 60
                      ? `${el.record}초`
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
    </>
  );
}
