import { useRecoilValue } from 'recoil';
import { getWorkoutTimeState } from '../../../recoil/selectors/getWorkoutTimeState';

export default function WorkOutTimeBox() {
  const data = useRecoilValue(getWorkoutTimeState);
  const records = data.records;
  const today = new Date();
  const todayRecord =
    records.length === 0 ? 0 : records.find((el) => el.data === String(today));
  console.log(todayRecord);
  const totalRecord = data.totalRecord;
  console.log();
  return (
    <section className="flex flex-col justify-center mb-5 mr-5 grow bg-pale_pink">
      <p className="p-3">일일 운동 시간</p>
      <p className="text-center">오늘 운동 시간(분) / 총 운동 시간(분)</p>
      <p className="text-center text-5xl">
        {0} / {0}
      </p>
    </section>
  );
}
