import WorkOutTimeBox from './components/WorkOutTimeBox';
import Calendar from './Calendar';
import Goal from './components/Goal';

export default function Summary() {
  return (
    <div className="m-2">
      <div className="flex w-full">
        <div className="flex flex-col grow">
          <WorkOutTimeBox />
          <section className="grow mr-5 bg-pale_pink">
            <Goal />
          </section>
        </div>
        <section className="flex justify-center grow w-1/2 h-full">
          <Calendar />
        </section>
      </div>
      <section className="">
        <p>최근 시청한 운동 영상</p>
      </section>
    </div>
  );
}
