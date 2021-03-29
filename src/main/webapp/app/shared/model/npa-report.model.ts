import dayjs from 'dayjs';

export interface INpaReport {
  id?: number;
  npaId?: number | null;
  typeOfCode?: string | null;
  assignable?: string | null;
  explanation?: string | null;
  reserved?: string | null;
  assigned?: string | null;
  assignmentDate?: string | null;
  useType?: string | null;
  location?: string | null;
  country?: string | null;
  inService?: string | null;
  inServiceDate?: string | null;
  status?: string | null;
  planningLetters?: string | null;
  notes?: string | null;
  overlay?: string | null;
  overlayComplex?: string | null;
  parentNpaId?: number | null;
  service?: string | null;
  timeZone?: string | null;
  areaServed?: string | null;
  map?: string | null;
  inJeopardy?: string | null;
  reliefPlanningInProgress?: string | null;
  homeNpaLocalCalls?: string | null;
  homeNpaTollCalls?: string | null;
  foreignNpaLocalCalls?: string | null;
  foreignNpaTollCalls?: string | null;
  permHnpaLocalCalls?: string | null;
  permHnpaTollCalls?: string | null;
  permHnpaForeignLocalCalls?: string | null;
  dialingPlanNotes?: string | null;
}

export const defaultValue: Readonly<INpaReport> = {};
