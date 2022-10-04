import { useState } from 'react';
import CalendarApp from 'react-calendar';
import './Calendar.css';

export default function Calendar() {
  // const [value, onChange] = useState(new Date());
  return (
    <>
      <CalendarApp
        // onChange={onChange}
        // value={value}
        showNeighboringMonth={false}
        // tileContent={({ date, view }) => {
        //   // 날짜 타일에 컨텐츠 추가하기 (html 태그)
        //   // 추가할 html 태그를 변수 초기화
        //   let html = [];
        //   // 현재 날짜가 post 작성한 날짜 배열(mark)에 있다면, dot div 추가
        //   if (mark.find((x) => x === moment(date).format('YYYY-MM-DD'))) {
        //     html.push(<div className="dot"></div>);
        //   }
        //   // 다른 조건을 주어서 html.push 에 추가적인 html 태그를 적용할 수 있음.
        //   return (
        //     <>
        //       <div className="flex justify-center items-center absoluteDiv">
        //         {html}
        //       </div>
        //     </>
        //   );
        // }}
      ></CalendarApp>
    </>
  );
}
