import Sidebar from './Sidebar';

export default function MyPage({ children }) {
  return (
    <div className="flex sm:flex-col sm:min-h-[calc(100vh-144px)] min-h-[calc(100vh-160px)] md:bg-split-green-pink bg-sm-split-green-pink">
      <Sidebar />
      <main className="flex flex-col justify-center items-center w-full bg-gray md:m-3 rounded-xl">
        {children}
      </main>
    </div>
  );
}
