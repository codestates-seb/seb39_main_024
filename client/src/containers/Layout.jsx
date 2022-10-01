export default function Layout({ children }) {
  return (
    <main className="flex justify-center items-center h-full">
      <section className="sm:max-w-480 w-768">{children}</section>
    </main>
  );
}
