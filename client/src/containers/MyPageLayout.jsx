import Sidebar from './Sidebar';

export default function MyPage({ children }) {
  return (
    <div className="flex sm:flex-col sm:min-h-[calc(100vh-144px)] min-h-[calc(100vh-160px)] md:bg-split-green-pink bg-sm-split-green-pink">
      <Sidebar />
      <main className="flex flex-col justify-center w-full bg-pale_pink md:m-3">
        {children}
      </main>
    </div>
  );
}
