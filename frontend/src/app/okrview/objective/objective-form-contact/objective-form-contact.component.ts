import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { NEVER } from 'rxjs';
import { UserService } from '../../../shared/services/helper/user.service';
import { User } from '../../../shared/model/api/user';
import { ViewObjective } from '../../../shared/model/ui/view-objective';
import { OkrUnit } from '../../../shared/model/ui/OrganizationalUnit/okr-unit';

interface ObjectiveFormData {
  objective?: ViewObjective;
  unitId?: number;
  currentItem: OkrUnit;
}

@Component({
  selector: 'app-objective-form-contact',
  templateUrl: './objective-form-contact.component.html',
  styleUrls: ['./objective-form-contact.component.scss']
})
export class ObjectiveFormContactComponent implements OnInit {
  objectiveForm: FormGroup;
  objective: ViewObjective;

  sendViaMicrosoftTeams: boolean = false;
  msTeamsDeepLink: string = 'https://teams.microsoft.com/l/chat/0/0';
  msTeamsDeepLinkMsg: AbstractControl;

  constructor(
    private dialogRef: MatDialogRef<ObjectiveFormContactComponent>,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) private formData: ObjectiveFormData
  ) {
  }

  ngOnInit(): void {
    this.objectiveForm = new FormGroup({
      msTeamsDeepLinkMsg: new FormControl('', [Validators.required, Validators.maxLength(1024)]),
    });

    if (this.formData.objective) {
      this.objective = this.formData.objective;
    }
  }

  getContactPersonEMail(): string {
    return this.getContactPerson().email;
  }

  getContactPersonName(): string {
    return `${this.getContactPerson().surname} ${this.getContactPerson().givenName}`;
  }

  private getContactPerson(): User {
    let contactPerson: User;
    this.userService.getUserById$(this.objective.contactPersonId)
      .subscribe(
      (user: User) => {
        contactPerson = user;
      });
    return contactPerson;
  }

  clickedSendViaMicrosoftTeams(): void {
    this.sendViaMicrosoftTeams = true;
  }

  closeDialog(): void {
    this.dialogRef.close(NEVER);
  }

  sendMsgToContactPersonMSTeams(): void {
    this.msTeamsDeepLinkMsg = this.objectiveForm.get('msTeamsDeepLinkMsg');
    this.msTeamsDeepLink = `${this.msTeamsDeepLink}?users=&message=${this.msTeamsDeepLinkMsg}`;
    console.log(this.msTeamsDeepLink);
  }
}
