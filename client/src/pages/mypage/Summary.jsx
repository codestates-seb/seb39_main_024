// import { useRecoilValue } from 'recoil';
// import { getWorkoutTimeState } from '../../recoil/selectors/getWorkoutTimeState';
import Calendar from './Calendar';

export default function Summary() {
  // const data = useRecoilValue(getWorkoutTimeState);
  // const todayRecord = data.records[0].record;
  // const totalRecord = data.totalRecord;

  return (
    <div className="m-2">
      <div className="flex w-full">
        <div className="flex flex-col grow">
          <section className="flex flex-col justify-center mb-5 mr-5 grow bg-pale_pink">
            <text className="p-3">일일 운동 시간</text>
            <text className="text-center">오늘 운동 시간 / 총 운동 시간</text>
            <text className="text-center text-5xl">
              {/* {todayRecord} / {totalRecord} */}
            </text>
          </section>
          <section className="grow mr-5 bg-pale_pink">
            <text>목표 달성 스티커</text>
            <div className="w-3/5"></div>
          </section>
        </div>
        <section className="flex justify-center grow w-1/2 h-full">
          <Calendar />
        </section>
      </div>
      <section className="">
        <text>최근 시청한 운동 영상</text>
      </section>
    </div>
  );
}
