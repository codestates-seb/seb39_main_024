import WorkOutTimeBox from './components/WorkOutTimeBox';
import Calendar from './Calendar';
import Goal from './components/Goal';
import VideoHistory from './components/VideoHistory';

export default function Summary() {
  return (
    <div className="sm:flex sm:p-2 md:pl-[5%] sm:flex-col w-full px-[10px] z-0 md:py-6 h-full">
      <div className="flex sm:flex-col md:flew-row grow basis-3/4">
        <div className="flex flex-col md:mr-5 sm:mb-5 sm:px-3 grow">
          <WorkOutTimeBox />
          <Goal />
        </div>
        <div className="flex justify-center items-center sm:my-5 sm:mx-3 h-full md:w-1/2 bg-light_pink rounded-[8px] py-4">
          <Calendar summary={'summary'} />
        </div>
      </div>
      <div className="pt-4 sm:px-2">
        <VideoHistory />
      </div>
    </div>
  );
}
