import { AbstractEmitterVisitor } from '@angular/compiler/src/output/abstract_emitter';
import { LabeledIcon } from './labeledIcon';

export interface LinkIcon extends LabeledIcon {
  link: string;
}