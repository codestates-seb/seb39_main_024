import { ReactNode } from 'react';

type Props = {
  children: ReactNode;
};

export default function Layout(props: Props) {
  return (
    <main className="flex justify-center items-center h-full">
      <section className="sm:max-w-480 w-768">{props.children}</section>
    </main>
  );
}

// border-solid border-2 border-indigo-600
