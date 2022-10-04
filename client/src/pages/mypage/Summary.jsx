import { useRecoilValue } from 'recoil';
import { getWorkoutTimeState } from '../../recoil/selectors/getWorkoutTimeState';
import Calendar from './Calendar';

export default function Summary() {
  const data = useRecoilValue(getWorkoutTimeState);
  const todayRecord = data.records[0].record;
  const totalRecord = data.totalRecord;
  // const memberId = useRecoilValue(getWorkoutTimeState);

  // useEffect(() => {
  //   instance
  //     .get(`/members/${memberId}`)
  //     .then((res) => console.log(memberId, res));
  // }, []);

  return (
    <>
      <div>
        <p>일일 운동 시간</p>
        <div>
          {todayRecord} / {totalRecord}
        </div>
      </div>
      <div>
        <p>목표 달성 스티커</p>
        <Calendar></Calendar>
      </div>
    </>
  );
}
