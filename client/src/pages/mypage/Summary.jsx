import WorkOutTimeBox from './components/WorkOutTimeBox';
import Calendar from './Calendar';
import Goal from './components/Goal';

export default function Summary() {
  return (
    <div className="sm:flex m-2 sm:flex-col w-full px-[14px] md:pl-[5%] z-0">
      <div className="flex sm:flex-col md:flew-row grow">
        <div className="flex flex-col md:mr-5 sm:mb-5 grow">
          <WorkOutTimeBox />
          <Goal />
        </div>
        <Calendar summary={'summary'} />
      </div>
      {/* <section className="">
        <p>최근 시청한 운동 영상</p>
      </section> */}
    </div>
  );
}
