export default interface Project {
  id: number;
  title: string;
  link: string | null;
  linkText: string;
  github: string;
  skills: string;
  image: string;
  imageAlt: string;
  description: string;
  isActive: boolean;
}