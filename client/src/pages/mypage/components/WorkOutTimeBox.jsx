import { useRecoilValue } from 'recoil';
import { getMemberInfoState } from '../../../recoil/selectors/getMemberInfoState';

export default function WorkOutTimeBox() {
  const data = useRecoilValue(getMemberInfoState);
  const records = data.records;
  const date = new Date();
  const today = date.toISOString().slice(0, 10);
  const todayRecord =
    records.length === 0
      ? 0
      : records.some((el) => el.date === today)
      ? Math.floor(records.find((el) => el.date === today).record / 60)
      : 0;
  const totalRecord = Math.floor(data.totalRecord / 60);

  return (
    <section className="flex flex-col p-4 mb-4 bg-light_pink rounded-[8px]">
      <p className="font-extrabold mb-3">일일 운동 시간</p>
      <div className="flex flex-col justify-center itmes-center">
        <p className="text-center text-xs">
          오늘 운동 시간(분) / 총 운동 시간(분)
        </p>
        <p className="text-center text-5xl">
          {todayRecord} / {totalRecord}
        </p>
      </div>
    </section>
  );
}
